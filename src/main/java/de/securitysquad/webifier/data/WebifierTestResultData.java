package de.securitysquad.webifier.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResult;

/**
 * Created by samuel on 17.02.17.
 */
public class WebifierTestResultData {
    @JsonProperty
    private final WebifierTestParameters testData;
    @JsonProperty
    private final String testId;
    @JsonProperty
    private final TestResult result;
    @JsonProperty
    private final long duration;

    public WebifierTestResultData(String testId, WebifierTestParameters testData, TestResult result, long duration) {
        this.testId = testId;
        this.testData = testData;
        this.result = result;
        this.duration = duration;
    }

    public String getTestId() {
        return testId;
    }

    public WebifierTestParameters getTestData() {
        return testData;
    }

    public TestResult getResult() {
        return result;
    }

    public long getDuration() {
        return duration;
    }
}
