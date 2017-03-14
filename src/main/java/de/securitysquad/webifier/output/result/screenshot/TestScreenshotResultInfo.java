package de.securitysquad.webifier.output.result.screenshot;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResultInfo;

import java.util.ArrayList;
import java.util.List;

public class TestScreenshotResultInfo extends TestResultInfo {

    @JsonProperty("base64img")
    private String base64img;

    public TestScreenshotResultInfo() {
        base64img = "";
    }

    public String getBase64img() {
        return base64img;
    }
}
