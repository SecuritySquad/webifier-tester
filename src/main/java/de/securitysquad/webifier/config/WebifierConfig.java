package de.securitysquad.webifier.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by samuel on 08.11.16.
 */
public class WebifierConfig {
    @JsonProperty("resolver")
    private WebifierTestData resolver;
    @JsonProperty("tests")
    private List<WebifierTestData> tests;

    public WebifierTestData getResolver() {
        return resolver;
    }

    public List<WebifierTestData> getTests() {
        return tests;
    }
}
