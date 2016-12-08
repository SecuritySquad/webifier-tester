package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 09.11.16.
 */
public class TestVirusScanResult extends TestResult {
    @JsonProperty("info")
    private TestVirusScanResultInfo info;

    public TestVirusScanResultInfo getInfo() {
        return info;
    }
}