package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestPortScanResult extends TestResult {
    @JsonProperty("info")
    private TestPortScanResultInfo info;

    public TestPortScanResultInfo getInfo() {
        return info;
    }
}
