package de.securitysquad.webifier.test;

/**
 * Created by samuel on 07.11.16.
 */
public interface WebifierTestListener<R> {
    void onTestStarted(WebifierTest test);

    void onTestFinished(WebifierTest test, R result);

    void onTestError(WebifierTest test, Exception exception);

    Class<R> getResultClass();
}
