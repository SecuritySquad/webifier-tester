package de.securitysquad.webifier;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTester {
    private URL url;
    private boolean asynchronous;

    public WebifierTester(String url, boolean asynchronous) throws MalformedURLException {
        this.url = new URL(url);
        this.asynchronous = asynchronous;
    }

    public void launch() {
        System.out.println("Starting " + (asynchronous ? "asynchronous " : "") + "launch for " + url);
        // TODO trigger tests
    }
}