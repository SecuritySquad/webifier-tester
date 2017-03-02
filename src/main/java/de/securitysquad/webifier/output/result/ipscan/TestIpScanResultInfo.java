package de.securitysquad.webifier.output.result.ipscan;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResultInfo;

import java.util.ArrayList;
import java.util.List;

public class TestIpScanResultInfo extends TestResultInfo {

    @JsonProperty("risky_hosts")
    private List<Integer> risky_hosts;

    public TestIpScanResultInfo() {
        risky_hosts = new ArrayList<>();
    }

    public List<Integer> getUnknownPorts() {
        return risky_hosts;
    }
}
