package de.securitysquad.webifier.test;

import de.securitysquad.webifier.config.WebifierTestData;
import de.securitysquad.webifier.output.message.TesterFinished;
import de.securitysquad.webifier.output.message.TesterStart;
import de.securitysquad.webifier.output.message.test.TestFinishedWithError;
import de.securitysquad.webifier.output.message.test.TestFinishedWithResult;
import de.securitysquad.webifier.output.message.test.TestStarted;
import de.securitysquad.webifier.output.result.TestResult;
import de.securitysquad.webifier.output.result.WebifierResultType;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTester implements WebifierTestListener<TestResult> {
    private final String suitId;
    private final String url;
    private final OutputFormat output;
    private final List<WebifierTest<TestResult>> tests;

    public WebifierTester(String id, String url, OutputFormat output, List<WebifierTestData> testData) {
        this.suitId = id;
        this.url = url;
        this.output = output;
        this.tests = testData.stream().filter(WebifierTestData::isEnabled).map(data -> new WebifierTest<>(suitId, url, data, this)).collect(toList());
    }

    public void launch() {
        output.print(new TesterStart(suitId, url));
        tests.forEach(WebifierTest::launch);
    }

    @Override
    public void onTestStarted(WebifierTest test) {
        output.print(new TestStarted(suitId, test.getId(), test.getData().getName()));
    }

    @Override
    public void onTestFinished(WebifierTest test, TestResult result) {
        output.print(new TestFinishedWithResult(suitId, test.getId(), test.getData().getName(), result));
        if (tests.stream().allMatch(WebifierTest::isCompleted)) {
            output.print(new TesterFinished(suitId, url, calculateOverallResult()));
        }
    }

    private WebifierResultType calculateOverallResult() {
        // TODO calculate overall result
        if (tests.stream().allMatch(t -> t.getResult().getResultType() == WebifierResultType.CLEAN)) {
            return WebifierResultType.CLEAN;
        }
        return WebifierResultType.MALICIOUS;
    }

    @Override
    public void onTestError(WebifierTest test, Exception exception) {
        output.print(new TestFinishedWithError(suitId, test.getId(), test.getData().getName(), exception));
    }

    @Override
    public Class<TestResult> getResultClass() {
        return TestResult.class;
    }
}