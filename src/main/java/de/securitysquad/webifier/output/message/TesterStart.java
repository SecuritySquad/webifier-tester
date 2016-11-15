package de.securitysquad.webifier.output.message;

import java.net.URL;

/**
 * Created by samuel on 09.11.16.
 */
public class TesterStart extends Message {
    public TesterStart(String testerId, URL url) {
        super(testerId, "Start Tester for url " + url);
    }
}
