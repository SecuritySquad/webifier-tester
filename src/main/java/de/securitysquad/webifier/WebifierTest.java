package de.securitysquad.webifier;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTest {
    private WebifierTestData data;
    private Thread thread;
    private Process process;

    public WebifierTest(WebifierTestData data) {
        // TODO init test
        this.data = data;
    }

    public WebifierTestData getData() {
        return data;
    }

    public void launch(WebifierTestListener listener) {
        // TODO launch test
    }
}
