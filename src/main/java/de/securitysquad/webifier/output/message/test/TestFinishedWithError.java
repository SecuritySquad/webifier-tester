package de.securitysquad.webifier.output.message.test;

/**
 * Created by samuel on 09.11.16.
 */
public class TestFinishedWithError extends TestErrorMessage {
    public TestFinishedWithError(String testerId, String testId, String testName, Exception exception) {
        super(testerId, testId, testName, exception);
    }
}
