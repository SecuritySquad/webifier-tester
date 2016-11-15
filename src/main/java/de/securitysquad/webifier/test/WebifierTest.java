package de.securitysquad.webifier.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.securitysquad.webifier.config.WebifierTestData;
import de.securitysquad.webifier.output.result.TestResult;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTest implements WebifierTestResultListener {
    private final String id;
    private final String url;
    private final WebifierTestData data;
    private final WebifierTestListener listener;
    private TestResult result;

    public WebifierTest(String suitId, String url, WebifierTestData data, WebifierTestListener listener) {
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
        return result != null;
    }

    public TestResult getResult() {
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
                process.waitFor(5, TimeUnit.MINUTES);
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
                process.waitFor(1, TimeUnit.MINUTES);
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
            this.result = mapper.readValue(result, getSpecificClass());
        } catch (IOException e) {
            listener.onTestError(this, e);
        }
        shutdown();
        if (result != null) {
            listener.onTestFinished(WebifierTest.this, this.result);
        }
    }

    private Class<? extends TestResult> getSpecificClass() {
        try {
            return (Class<? extends TestResult>) Class.forName(data.getResultClass());
        } catch (Exception e) {
            return TestResult.class;
        }
    }

    @Override
    public void onTestError(String error) {
        listener.onTestError(this, new Exception(error));
    }
}
