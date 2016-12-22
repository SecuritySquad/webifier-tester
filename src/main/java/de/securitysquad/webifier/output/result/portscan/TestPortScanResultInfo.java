package de.securitysquad.webifier.output.result.portscan;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResultInfo;

import java.util.ArrayList;
import java.util.List;

public class TestPortScanResultInfo extends TestResultInfo {

    @JsonProperty("unknown_ports")
    private List<Integer> unknown_ports;

    public TestPortScanResultInfo() {
        unknown_ports = new ArrayList<>();
    }

    public List<Integer> getUnknownPorts() {
        return unknown_ports;
    }
}
