package de.securitysquad.webifier.output.result.headerinspection;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResult;

public class HeaderInspectionResult extends TestResult {
    @JsonProperty("info")
    private HeaderInspectionResultInfo info;

    public HeaderInspectionResultInfo getInfo() {
        return info;
    }
}
