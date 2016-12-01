package de.securitysquad.webifier.output.message;

import de.securitysquad.webifier.output.message.test.TestErrorMessage;

/**
 * Created by samuel on 01.12.16.
 */
public class ResolverFinishedWithError extends TestErrorMessage {
    public ResolverFinishedWithError(String testerId, String testId, Exception exception) {
        super(testerId, testId, "resolver", exception);
    }
}
