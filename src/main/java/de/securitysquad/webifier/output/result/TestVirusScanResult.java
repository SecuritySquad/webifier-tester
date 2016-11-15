package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 09.11.16.
 */
public class TestVirusScanResult extends TestResult {
    @JsonProperty("info")
    private VirusScanResult info;

    public VirusScanResult getInfo() {
        return info;
    }

    public void setInfo(VirusScanResult info) {
        this.info = info;
    }
}