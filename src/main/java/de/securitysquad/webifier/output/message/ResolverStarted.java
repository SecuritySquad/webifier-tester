package de.securitysquad.webifier.output.message;

import de.securitysquad.webifier.output.message.test.TestMessage;

/**
 * Created by samuel on 01.12.16.
 */
public class ResolverStarted extends TestMessage {
    public ResolverStarted(String testerId, String testId, String url) {
        super(testerId, testId, "resolver", "Resolver started for url " + url);
    }
}
