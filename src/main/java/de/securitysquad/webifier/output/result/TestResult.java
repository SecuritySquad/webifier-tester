package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 09.11.16.
 */
public class TestResult {
    @JsonProperty("malicious")
    private boolean malicious;

    public boolean isMalicious() {
        return malicious;
    }
}
