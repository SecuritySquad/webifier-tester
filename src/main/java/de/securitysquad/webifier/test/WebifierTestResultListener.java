package de.securitysquad.webifier.test;

/**
 * Created by samuel on 09.11.16.
 */
public interface WebifierTestResultListener {
    void onTestResult(String result);

    void onTestError(String error);
}
