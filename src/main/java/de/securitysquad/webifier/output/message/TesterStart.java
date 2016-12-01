package de.securitysquad.webifier.output.message;

/**
 * Created by samuel on 09.11.16.
 */
public class TesterStart extends Message {
    public TesterStart(String testerId, String url) {
        super(testerId, "Start Tester for url " + url);
    }
}
