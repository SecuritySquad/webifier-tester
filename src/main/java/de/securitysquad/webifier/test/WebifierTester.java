package de.securitysquad.webifier.test;

import de.securitysquad.webifier.config.WebifierConfig;
import de.securitysquad.webifier.config.WebifierTestData;
import de.securitysquad.webifier.data.WebifierTestParameters;
import de.securitysquad.webifier.data.WebifierTestResultData;
import de.securitysquad.webifier.data.WebifierTesterResultData;
import de.securitysquad.webifier.output.message.TesterFinished;
import de.securitysquad.webifier.output.message.TesterStart;
import de.securitysquad.webifier.output.message.test.TestFinished;
import de.securitysquad.webifier.output.message.test.TestStarted;
import de.securitysquad.webifier.output.result.TestResult;
import de.securitysquad.webifier.output.result.WebifierResultType;
import de.securitysquad.webifier.output.result.phishingdetector.TestPhishingDetectorResultInfo;
import de.securitysquad.webifier.output.result.phishingdetector.TestPhishingDetectorResultMatch;

import java.util.List;
import java.util.Objects;

import static de.securitysquad.webifier.data.WebifierData.pushResult;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.stream.Collectors.toList;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTester implements WebifierTestListener<TestResult> {
    private static final double MAX_UNDEFINED_TEST_PERCENTAGE = 0.4;

    private final String suitId;
    private final String originalUrl;
    private final String url;
    private final OutputFormat output;
    private final List<WebifierTest<TestResult>> tests;
    private WebifierConfig config;
    private long startTimestamp;
    private WebifierOverallTestResult overallResult;

    public WebifierTester(String id, String originalUrl, String url, OutputFormat output, WebifierConfig config) {
        this.suitId = id;
        this.originalUrl = originalUrl;
        this.url = url;
        this.output = output;
        this.tests = config.getTests().stream().filter(WebifierTestData::isEnabled)
                .map(data -> new WebifierTest<>(suitId, url, data, this)).collect(toList());
        this.config = config;
    }

    public void launch() {
        startTimestamp = System.currentTimeMillis();
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
        finishTesterIfTestsComplete();
    }

    private void finishTesterIfTestsComplete() {
        System.out.println(overallResult != null);
        System.out.println(tests.stream().map(WebifierTest::isCompleted).collect(toList()));
        if (overallResult != null) {
            return;
        }
        if (tests.stream().allMatch(WebifierTest::isCompleted)) {
            long endTimestamp = System.currentTimeMillis();
            overallResult = calculateOverallResult();
            output.print(new TesterFinished(suitId, url, overallResult.getResultType()));
            Boolean push_result_data = config.getPreferenceValue("push_result_data", true);
            System.out.println(push_result_data);
            if (push_result_data) {
                if (!pushResult(collectTesterResultData(overallResult, endTimestamp - startTimestamp))) {
                    System.out.println("Failed to push result to webifier-data!");
                }
            }
        }
    }

    private WebifierTesterResultData collectTesterResultData(WebifierOverallTestResult result, long testerDuration) {
        List<WebifierTestResultData> testResults = tests.stream().map(this::mapTestResultData).filter(Objects::nonNull).collect(toList());
        return new WebifierTesterResultData(suitId, originalUrl, url, result, testerDuration, testResults);
    }

    private WebifierTestResultData mapTestResultData(WebifierTest<TestResult> test) {
        if ("Screenshot".equals(test.getData().getName())) {
            return null;
        }
        TestResult result = test.getResult().clone();
        if ("PhishingDetector".equals(test.getData().getName())) {
            ((TestPhishingDetectorResultInfo) result.getInfo()).getMatches().forEach(TestPhishingDetectorResultMatch::removeComparison);
        }
        return new WebifierTestResultData(test.getId(), mapTestParameters(test.getData()), result, test.getDuration());
    }

    private WebifierTestParameters mapTestParameters(WebifierTestData data) {
        return new WebifierTestParameters(data.getName(), data.getStartup(), data.getShutdown(), data.isEnabled(),
                data.getWeight(), data.getStartupTimeoutInSeconds(), data.getShutdownTimeoutInSeconds());
    }

    private WebifierOverallTestResult calculateOverallResult() {
        int weightSum = tests.stream().map(WebifierTest::getData).mapToInt(WebifierTestData::getWeight).sum();
        int undefinedTestSum = tests.stream().filter(test -> test.getResult().getResultType() == WebifierResultType.UNDEFINED)
                .map(WebifierTest::getData).mapToInt(WebifierTestData::getWeight).sum();
        double undefinedPercentage = (double) undefinedTestSum / (double) weightSum;
        if (undefinedPercentage > MAX_UNDEFINED_TEST_PERCENTAGE) {
            return new WebifierOverallTestResult(WebifierResultType.UNDEFINED);
        }
        double result = 0;
        for (WebifierTest<TestResult> test : tests) {
            double testWeight = (double) test.getData().getWeight() / (double) weightSum;
            result += getTestResultValue(test.getResult().getResultType(), testWeight) * testWeight;
        }
        if (result >= 0.5) {
            return new WebifierOverallTestResult(WebifierResultType.MALICIOUS, result);
        }
        if (result >= 0.1) {
            return new WebifierOverallTestResult(WebifierResultType.SUSPICIOUS, result);
        }
        return new WebifierOverallTestResult(WebifierResultType.CLEAN, result);
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
        finishTesterIfTestsComplete();
    }

    @Override
    public Class<TestResult> getResultClass() {
        return TestResult.class;
    }
}