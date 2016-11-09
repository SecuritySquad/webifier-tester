package de.securitysquad.webifier.test;

import de.securitysquad.webifier.config.WebifierTestData;
import de.securitysquad.webifier.result.TestResult;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTester implements WebifierTestListener {
    private final String suitId;
    private final URL url;
    private final OutputFormat output;
    private final List<WebifierTest> tests;

    public WebifierTester(String url, OutputFormat output, List<WebifierTestData> testData) throws MalformedURLException {
        this.suitId = UUID.randomUUID().toString();
        this.url = new URL(url);
        this.output = output;
        this.tests = testData.stream().map(data -> new WebifierTest(suitId, url, data, this)).collect(toList());
    }

    public void launch() {
        output.print("Starting tests for " + url);
        tests.forEach(WebifierTest::launch);
    }

    @Override
    public void onTestStarted(WebifierTest test) {
        output.print("Test " + test.getData().getName() + " started!");
    }

    @Override
    public void onTestFinished(WebifierTest test, TestResult result) {
        output.print("Test " + test.getData().getName() + " finished! Result:");
        output.print(result);
        // TODO handle result
    }

    @Override
    public void onTestError(WebifierTest test, Exception exception) {
        output.print("Test " + test.getData().getName() + " finished with error!");
        exception.printStackTrace();
    }
}