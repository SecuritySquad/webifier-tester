package de.securitysquad.webifier.output.result.headerinspection;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResultInfo;

import java.util.List;

public class HeaderInspectionResultInfo extends TestResultInfo {
    @JsonProperty
    private double medianRatio;
    @JsonProperty
    private double worstRatio;
    @JsonProperty
    private int medianDiff;
    @JsonProperty
    private int worstDiff;
    @JsonProperty
    private List<String> browsers;

    public double getMedianRatio() {
        return medianRatio;
    }

    public double getWorstRatio() {
        return worstRatio;
    }

    public int getMedianDiff() {
        return medianDiff;
    }

    public int getWorstDiff() {
        return worstDiff;
    }

    public List<String> getBrowsers() {
        return browsers;
    }
}
