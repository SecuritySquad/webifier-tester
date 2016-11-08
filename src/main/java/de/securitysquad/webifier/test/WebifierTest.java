package de.securitysquad.webifier.test;

import de.securitysquad.webifier.config.WebifierTestData;
import org.apache.commons.exec.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTest {
    private final WebifierTestData data;

    public WebifierTest(WebifierTestData data) {
        this.data = data;
    }

    public WebifierTestData getData() {
        return data;
    }

    public void launch(WebifierTestListener listener) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CommandLine commandLine = CommandLine.parse(data.getScript());
            DefaultExecutor executor = new DefaultExecutor();
            executor.setProcessDestroyer(new ShutdownHookProcessDestroyer());
            // TODO set timeout
            // executor.setWatchdog(new ExecuteWatchdog(10000));
            executor.setStreamHandler(new PumpStreamHandler(outputStream));
            executor.execute(commandLine, new ExecuteResultHandler() {
                @Override
                public void onProcessComplete(int exitValue) {
                    listener.onTestFinished(WebifierTest.this, outputStream.toString());
                }

                @Override
                public void onProcessFailed(ExecuteException e) {
                    listener.onTestError(WebifierTest.this, e);
                }
            });
            listener.onTestStarted(this);
        } catch (IOException e) {
            listener.onTestError(this, e);
        }
    }
}
