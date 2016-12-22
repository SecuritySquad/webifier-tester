package de.securitysquad.webifier.test;

/**
 * Created by samuel on 07.11.16.
 */
public interface WebifierTestListener<R> {
    void onTestStarted(WebifierTest<R> test);

    void onTestFinished(WebifierTest<R> test, R result);

    void onTestError(WebifierTest<R> test, Exception exception, R result);

    Class<R> getResultClass();
}
