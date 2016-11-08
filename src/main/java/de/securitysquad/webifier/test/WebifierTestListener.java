package de.securitysquad.webifier.test;

/**
 * Created by samuel on 07.11.16.
 */
interface WebifierTestListener {
    void onTestStarted(WebifierTest test);

    // TODO create test result object
    void onTestFinished(WebifierTest test, Object result);

    void onTestError(WebifierTest test, Exception exception);
}
