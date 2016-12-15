package de.securitysquad.webifier.output.result.headerinspection;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResultInfo;

public class HeaderInspectionResultInfo {
    @JsonProperty("ratio")
    private int ratio;

    public int getRatio() {
        return ratio;
    }
}
