package de.securitysquad.webifier;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTester implements WebifierTestListener {
    private URL url;
    private OutputFormat output;
    private List<WebifierTest> tests;

    public WebifierTester(String url, OutputFormat output, List<WebifierTestData> testData) throws MalformedURLException {
        this.url = new URL(url);
        this.output = output;
        this.tests = testData.stream().map(WebifierTest::new).collect(toList());
    }

    public void launch() {
        output.print("Starting tests for " + url);
        tests.forEach(test -> test.launch(this));
    }

    @Override
    public void onTestStarted(WebifierTest test, int processId) {
        output.print("Test " + test.getData().getName() + " started with id " + processId);
    }

    @Override
    public void onTestFinished(WebifierTest test, Object result) {
        output.print("Test " + test.getData().getName() + " finished!");
        // TODO handle result
    }
}