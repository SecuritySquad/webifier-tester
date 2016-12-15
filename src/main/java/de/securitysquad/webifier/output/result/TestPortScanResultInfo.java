package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TestPortScanResultInfo {

    @JsonProperty("unknown_ports")
    private List<Integer> unknown_ports;

    public TestPortScanResultInfo() {
        unknown_ports = new ArrayList<>();
    }

    public List<Integer> getUnknownPorts() {
        return unknown_ports;
    }
}
