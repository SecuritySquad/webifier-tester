package de.securitysquad.webifier;

/**
 * Created by samuel on 07.11.16.
 */
public interface WebifierTestListener {
    void onTestStarted(WebifierTest test, int processId);

    // TODO create test result object
    void onTestFinished(WebifierTest test, Object result);
}
