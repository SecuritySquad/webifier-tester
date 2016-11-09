package de.securitysquad.webifier.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.securitysquad.webifier.config.WebifierTestData;
import de.securitysquad.webifier.result.TestResult;
import org.apache.commons.exec.*;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTest implements ExecuteResultHandler, WebifierTestResultListener {
    private final String testId;
    private final String url;
    private final WebifierTestData data;
    private final WebifierTestListener listener;
    private TestResult result;

    public WebifierTest(String suitId, String url, WebifierTestData data, WebifierTestListener listener) {
        this.testId = suitId + "_" + UUID.randomUUID().toString();
        this.url = url;
        this.data = data;
        this.listener = listener;
    }

    public WebifierTestData getData() {
        return data;
    }

    public void launch() {
        try {
            CommandLine commandLine = createCommandLine(data.getStartup());
            DefaultExecutor executor = createExecutor();
            executor.setStreamHandler(new WebifierTestStreamHandler(testId + ": ", this));
            executor.execute(commandLine, this);
            listener.onTestStarted(this);
        } catch (IOException e) {
            listener.onTestError(this, e);
        }
    }

    private void shutdown() {
        if (Strings.isEmpty(data.getShutdown())) {
            return;
        }
        try {
            CommandLine commandLine = createCommandLine(data.getShutdown());
            DefaultExecutor executor = createExecutor();
            executor.execute(commandLine);
            listener.onTestStarted(this);
        } catch (IOException e) {
            listener.onTestError(this, e);
        }
    }

    private DefaultExecutor createExecutor() {
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(0);
        executor.setProcessDestroyer(new ShutdownHookProcessDestroyer());
        executor.setWatchdog(new ExecuteWatchdog(1000));
        return executor;
    }

    private CommandLine createCommandLine(String shutdown) {
        CommandLine commandLine = new CommandLine(shutdown);
        commandLine.addArgument(testId);
        commandLine.addArgument(url);
        return commandLine;
    }

    @Override
    public void onTestResult(String result) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.result = mapper.readValue(result, TestResult.class);
        } catch (IOException e) {
            listener.onTestError(this, e);
        }
        shutdown();
    }

    @Override
    public void onProcessComplete(int exitValue) {
        if (result != null) {
            listener.onTestFinished(WebifierTest.this, result);
        }
    }

    @Override
    public void onProcessFailed(ExecuteException e) {
        listener.onTestError(WebifierTest.this, e);
    }
}
