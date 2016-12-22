package de.securitysquad.webifier.test;

import de.securitysquad.webifier.config.WebifierTestData;
import de.securitysquad.webifier.output.message.TesterFinished;
import de.securitysquad.webifier.output.message.TesterStart;
import de.securitysquad.webifier.output.message.test.TestFinished;
import de.securitysquad.webifier.output.message.test.TestStarted;
import de.securitysquad.webifier.output.result.TestResult;
import de.securitysquad.webifier.output.result.WebifierResultType;

import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.stream.Collectors.toList;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTester implements WebifierTestListener<TestResult> {
    private static final double MAX_UNDEFINED_TEST_PERCENTAGE = 0.4;

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
    public void onTestFinished(WebifierTest<TestResult> test, TestResult result) {
        output.print(new TestFinished(suitId, test.getId(), test.getData().getName(), result));
        if (tests.stream().allMatch(WebifierTest::isCompleted)) {
            output.print(new TesterFinished(suitId, url, calculateOverallResult()));
        }
    }

    private WebifierResultType calculateOverallResult() {
        int weightSum = tests.stream().map(WebifierTest::getData).mapToInt(WebifierTestData::getWeight).sum();
        int undefinedTestSum = tests.stream().filter(test -> test.getResult().getResultType() == WebifierResultType.UNDEFINED)
                .map(WebifierTest::getData).mapToInt(WebifierTestData::getWeight).sum();
        double undefinedPercentage = (double) undefinedTestSum / (double) weightSum;
        if (undefinedPercentage > MAX_UNDEFINED_TEST_PERCENTAGE) {
            return WebifierResultType.UNDEFINED;
        }
        double result = 0;
        for (WebifierTest<TestResult> test : tests) {
            double testWeight = (double) test.getData().getWeight() / (double) weightSum;
            result += getTestResultValue(test.getResult().getResultType(), testWeight) * testWeight;
        }
        if (result >= 0.5) {
            return WebifierResultType.MALICIOUS;
        }
        if (result >= 0.1) {
            return WebifierResultType.SUSPICIOUS;
        }
        return WebifierResultType.CLEAN;
    }

    private double getTestResultValue(WebifierResultType type, double testWeight) {
        if (type == WebifierResultType.CLEAN) {
            return 0;
        }
        if (type == WebifierResultType.MALICIOUS) {
            return 1;
        }
        return min(0.5, max(0.1, testWeight));
    }

    @Override
    public void onTestError(WebifierTest<TestResult> test, Exception exception, TestResult result) {
        output.print(new TestFinished(suitId, test.getId(), test.getData().getName(), result));
    }

    @Override
    public Class<TestResult> getResultClass() {
        return TestResult.class;
    }
}