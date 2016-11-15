package de.securitysquad.webifier.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTestData {
    @JsonProperty("name")
    private String name;
    @JsonProperty("startup")
    private String startup;
    @JsonProperty("shutdown")
    private String shutdown;
    @JsonProperty("enabled")
    private boolean enabled;
    @JsonProperty("result_class")
    private String resultClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartup() {
        return startup;
    }

    public void setStartup(String startup) {
        this.startup = startup;
    }

    public String getShutdown() {
        return shutdown;
    }

    public void setShutdown(String shutdown) {
        this.shutdown = shutdown;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getResultClass() {
        return resultClass;
    }

    public void setResultClass(String resultClass) {
        this.resultClass = resultClass;
    }
}