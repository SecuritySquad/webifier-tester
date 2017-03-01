package de.securitysquad.webifier.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by samuel on 08.11.16.
 */
public class WebifierConfig {
    @JsonProperty("resolver")
    private WebifierTestData resolver;
    @JsonProperty("tests")
    private List<WebifierTestData> tests;
    @JsonProperty("preferences")
    private Map<String, Object> preferences;

    public WebifierTestData getResolver() {
        return resolver;
    }

    public List<WebifierTestData> getTests() {
        return tests;
    }

    public <T> T getPreferenceValue(String key, T defaultValue) {
        try {
            return (T) preferences.get(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}