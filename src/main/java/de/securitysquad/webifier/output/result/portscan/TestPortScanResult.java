package de.securitysquad.webifier.output.result.portscan;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResult;

public class TestPortScanResult extends TestResult {
    @JsonProperty("info")
    private TestPortScanResultInfo info;

    public TestPortScanResultInfo getInfo() {
        return info;
    }
}
