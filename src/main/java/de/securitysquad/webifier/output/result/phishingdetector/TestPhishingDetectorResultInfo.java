package de.securitysquad.webifier.output.result.phishingdetector;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResultInfo;

import java.util.List;

/**
 * Created by samuel on 15.11.16.
 */
public class TestPhishingDetectorResultInfo extends TestResultInfo {
    @JsonProperty("keywords")
    private List<String> keywords;

    @JsonProperty("matches")
    private List<TestPhishingDetectorResultMatch> matches;

    public List<String> getKeywords() {
        return keywords;
    }

    public List<TestPhishingDetectorResultMatch> getMatches() {
        return matches;
    }
}