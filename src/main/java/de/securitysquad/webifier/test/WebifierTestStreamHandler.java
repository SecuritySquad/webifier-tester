package de.securitysquad.webifier.test;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.util.DebugUtils;

import java.io.*;

/**
 * Created by samuel on 09.11.16.
 */
public class WebifierTestStreamHandler implements ExecuteStreamHandler, Runnable {

    private static final long STOP_TIMEOUT_ADDITION = 2000L;

    private Thread outputThread;

    private BufferedReader outputReader;

    /**
     * the last exception being caught
     */
    private IOException caught;

    private String prefix;
    private WebifierTestResultListener listener;

    public WebifierTestStreamHandler(String prefix, WebifierTestResultListener listener) {
        this.prefix = prefix;
        this.listener = listener;
    }


    @Override
    public void setProcessInputStream(OutputStream os) throws IOException {
        // not needed
    }

    @Override
    public void setProcessErrorStream(InputStream is) throws IOException {
        // not needed
    }

    @Override
    public void setProcessOutputStream(InputStream is) throws IOException {
        this.outputReader = new BufferedReader(new InputStreamReader(is));
        final Thread result = new Thread(this, "Exec Webifier Stream Pumper");
        result.setDaemon(true);
        outputThread = result;
    }

    @Override
    public void start() throws IOException {
        if (outputThread != null) {
            outputThread.start();
        }
    }

    @Override
    public void stop() throws IOException {
        stopThread(outputThread, STOP_TIMEOUT_ADDITION);

        if (caught != null) {
            throw caught;
        }
    }

    private void stopThread(final Thread thread, final long timeout) {
        if (thread != null) {
            try {
                if (timeout == 0) {
                    thread.join();
                } else {
                    final long timeToWait = timeout + STOP_TIMEOUT_ADDITION;
                    final long startTime = System.currentTimeMillis();
                    thread.join(timeToWait);
                    if (!(System.currentTimeMillis() < startTime + timeToWait)) {
                        final String msg = "The stop timeout of " + timeout + " ms was exceeded";
                        caught = new ExecuteException(msg, Executor.INVALID_EXITVALUE);
                    }
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
                final String msg = "Got exception while closing output stream";
                DebugUtils.handleException(msg, e);
            }
        }
    }
}
