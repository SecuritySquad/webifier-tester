package de.securitysquad.webifier.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.test.WebifierOverallTestResult;

import java.util.List;

/**
 * Created by samuel on 17.02.17.
 */
public class WebifierTesterResultData {
    @JsonProperty
    private final String id;
    @JsonProperty
    private final String enteredUrl;
    @JsonProperty
    private final String testedUrl;
    @JsonProperty
    private final WebifierOverallTestResult result;
    @JsonProperty
    private final long duration;
    @JsonProperty
    private final List<WebifierTestResultData> testResults;

    public WebifierTesterResultData(String id, String enteredUrl, String testedUrl, WebifierOverallTestResult result, long duration, List<WebifierTestResultData> testResults) {
        this.id = id;
        this.enteredUrl = enteredUrl;
        this.testedUrl = testedUrl;
        this.result = result;
        this.duration = duration;
        this.testResults = testResults;
    }

    public String getId() {
        return id;
    }

    public String getEnteredUrl() {
        return enteredUrl;
    }

    public String getTestedUrl() {
        return testedUrl;
    }

    public WebifierOverallTestResult getResult() {
        return result;
    }

    public long getDuration() {
        return duration;
    }

    public List<WebifierTestResultData> getTestResults() {
        return testResults;
    }
}