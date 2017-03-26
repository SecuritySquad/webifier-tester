package de.securitysquad.webifier.output.result.googlesafebrowsing;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResultInfo;

import java.util.List;

/**
 * Created by samuel on 27.03.17.
 */
public class TestGoogleSafeBrowsingResultInfo extends TestResultInfo {
    @JsonProperty
    private List<String> matches;

    public List<String> getMatches() {
        return matches;
    }
}
