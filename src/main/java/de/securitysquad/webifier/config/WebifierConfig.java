package de.securitysquad.webifier.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by samuel on 08.11.16.
 */
public class WebifierConfig {
    @JsonProperty("tests")
    private List<WebifierTestData> tests;

    public void setTests(List<WebifierTestData> tests) {
        this.tests = tests;
    }

    public List<WebifierTestData> getTests() {
        return tests;
    }
}
