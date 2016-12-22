package de.securitysquad.webifier.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.securitysquad.webifier.config.WebifierTestData;
import de.securitysquad.webifier.output.result.TestResult;
import de.securitysquad.webifier.output.result.TestResultInfo;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTest<R> implements WebifierTestResultListener {
    private final String id;
    private final String url;
    private final WebifierTestData data;
    private final WebifierTestListener<R> listener;
    private boolean finished;
    private R result;
    private Exception error;

    public WebifierTest(String suitId, String url, WebifierTestData data, WebifierTestListener<R> listener) {
        this.id = suitId + "_" + UUID.randomUUID().toString();
        this.url = url;
        this.data = data;
        this.listener = listener;
    }

    public WebifierTestData getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isCompleted() {
        return result != null || error != null;
    }

    public R getResult() {
        return result;
    }

    public void launch() {
        new Thread(() -> {
            WebifierTestStreamHandler streamHandler = new WebifierTestStreamHandler(id + ": ", this);
            Process process = null;
            try {
                String command = data.getStartup().replace("#URL", url).replace("#ID", id);
                process = Runtime.getRuntime().exec(command);
                streamHandler.setProcessOutputStream(process.getInputStream());
                streamHandler.setProcessErrorStream(process.getErrorStream());
                streamHandler.start();
                listener.onTestStarted(this);
                process.waitFor(data.getStartupTimeoutInSeconds(), TimeUnit.SECONDS);
            } catch (IOException | InterruptedException e) {
                listener.onTestError(this, e);
            } finally {
                if (process != null) {
                    process.destroy();
                }
                try {
                    streamHandler.stop();
                } catch (IOException e) {
                    listener.onTestError(this, e);
                }
                if (!finished) {
                    onTestError("no result received!");
                }
            }
        }).start();
    }

    private void shutdown() {
        if (Strings.isEmpty(data.getShutdown())) {
            return;
        }
        new Thread(() -> {
            Process process = null;
            try {
                String command = data.getShutdown().replace("#URL", url).replace("#ID", id);
                process = Runtime.getRuntime().exec(command);
                process.waitFor(data.getShutdownTimeoutInSeconds(), TimeUnit.SECONDS);
            } catch (IOException | InterruptedException e) {
                listener.onTestError(WebifierTest.this, e);
            } finally {
                if (process != null) {
                    process.destroy();
                }
            }
        }).start();
    }

    @Override
    public void onTestResult(String result) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (TestResult.class.isAssignableFrom(listener.getResultClass())) {
                SimpleModule module = new SimpleModule();
                module.addAbstractTypeMapping(TestResultInfo.class, getSpecificClass());
                mapper.registerModule(module);
            }
            this.result = mapper.readValue(result, listener.getResultClass());
        } catch (Exception e) {
            e.printStackTrace();
            listener.onTestError(this, e);
        }
        shutdown();
        if (result != null) {
            listener.onTestFinished(WebifierTest.this, this.result);
        }
        finished = true;
    }

    private Class<? extends TestResultInfo> getSpecificClass() throws ClassNotFoundException {
        return (Class<? extends TestResultInfo>) Class.forName(data.getResultClass());
    }

    @Override
    public void onTestError(String error) {
        shutdown();
        this.error = new Exception(error);
        listener.onTestError(this, this.error);
        finished = true;
    }
}
