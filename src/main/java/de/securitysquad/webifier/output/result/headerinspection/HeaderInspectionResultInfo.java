package de.securitysquad.webifier.output.result.headerinspection;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResultInfo;

import java.util.List;

public class HeaderInspectionResultInfo extends TestResultInfo {
    @JsonProperty("meanRatio")
    private double meanRatio;

    @JsonProperty("worstRatio")
    private double worstRatio;

    @JsonProperty("browsers")
    private List<String> browsers;

    public double getMeanRatio() {
        return meanRatio;
    }

    public double getWorstRatio() {
        return worstRatio;
    }

    public List<String> getBrowsers() {
        return browsers;
    }
}
