package de.securitysquad.webifier.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by samuel on 09.11.16.
 */
public class WebifierTestStreamHandler implements Runnable {
    private static final long STOP_TIMEOUT_ADDITION = 2000L;

    private Thread outputThread;
    private BufferedReader outputReader;

    /**
     * the last exception being caught
     */
    private String prefix;
    private WebifierTestResultListener listener;

    public WebifierTestStreamHandler(String prefix, WebifierTestResultListener listener) {
        this.prefix = prefix;
        this.listener = listener;
    }


    public void setProcessErrorStream(InputStream is) throws IOException {
    }

    public void setProcessOutputStream(InputStream is) throws IOException {
        this.outputReader = new BufferedReader(new InputStreamReader(is));
        outputThread = new Thread(this, "Exec Webifier Stream Pumper");
        outputThread.setDaemon(true);
    }

    public void start() throws IOException {
        if (outputThread != null) {
            outputThread.start();
        }
    }

    public void stop() throws IOException {
        stopThread(outputThread, STOP_TIMEOUT_ADDITION);
    }

    private void stopThread(final Thread thread, final long timeout) {
        if (thread != null) {
            try {
                if (timeout == 0) {
                    thread.join();
                } else {
                    final long timeToWait = timeout + STOP_TIMEOUT_ADDITION;
                    thread.join(timeToWait);
                }
            } catch (final InterruptedException e) {
                thread.interrupt();
            }
        }
    }

    @Override
    public void run() {
        String line;
        try {
            while ((line = outputReader.readLine()) != null) {
                if (line.startsWith(prefix)) {
                    String input = line.substring(prefix.length());
                    // TODO validate input
                    listener.onTestResult(input);
                }
            }
        } catch (final Exception e) {
            // nothing to do - happens quite often with watchdog
        } finally {
            try {
                outputReader.close();
            } catch (final IOException e) {
                // nothing to do
            }
        }
    }
}
