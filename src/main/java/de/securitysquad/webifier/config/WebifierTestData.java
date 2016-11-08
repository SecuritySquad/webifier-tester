package de.securitysquad.webifier.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTestData {
    @JsonProperty("name")
    private String name;
    @JsonProperty("script")
    private String script;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}