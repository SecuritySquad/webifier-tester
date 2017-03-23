package de.securitysquad.webifier.output.result.phishingdetector;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.WebifierResultType;

/**
 * Created by samuel on 28.02.17.
 */
public class TestPhishingDetectorResultMatch {
    @JsonProperty("url")
    private String url;
    @JsonProperty("result")
    private WebifierResultType result;
    @JsonProperty("ratio")
    private double ratio;
    @JsonProperty("html_ratio")
    private double htmlRatio;
    @JsonProperty("content_ratio")
    private double contentRatio;
    @JsonProperty("screenshot_ratio")
    private double screenshotRatio;
    @JsonProperty("comparison")
    private String comparison;

    public String getUrl() {
        return url;
    }

    public WebifierResultType getResult() {
        return result;
    }

    public double getRatio() {
        return ratio;
    }

    public double getHtmlRatio() {
        return htmlRatio;
    }

    public double getContentRatio() {
        return contentRatio;
    }

    public double getScreenshotRatio() {
        return screenshotRatio;
    }

    public String getComparison() {
        return comparison;
    }

    public void removeComparison() {
        comparison = null;
    }
}