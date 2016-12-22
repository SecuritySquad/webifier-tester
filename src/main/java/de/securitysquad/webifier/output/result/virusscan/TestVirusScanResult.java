package de.securitysquad.webifier.output.result.virusscan;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResult;

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