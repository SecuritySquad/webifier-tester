package de.securitysquad.webifier.output.message.test;

/**
 * Created by samuel on 09.11.16.
 */
public class TestStarted extends TestMessage {
    public TestStarted(String testerId, String testId, String testName) {
        super(testerId, testId, testName, "Test '" + testName + "' started!");
    }
}
