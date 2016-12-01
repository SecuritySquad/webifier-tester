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
    @JsonProperty("startup_timeout_seconds")
    private int startupTimeoutInSeconds;
    @JsonProperty("shutdown_timeout_seconds")
    private int shutdownTimeoutInSeconds;

    public WebifierTestData() {
        // set default values
        enabled = true;
        startupTimeoutInSeconds = 5 * 60;
        shutdownTimeoutInSeconds = 60;
    }

    public String getName() {
        return name;
    }

    public String getStartup() {
        return startup;
    }

    public String getShutdown() {
        return shutdown;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getResultClass() {
        return resultClass;
    }

    public int getStartupTimeoutInSeconds() {
        return startupTimeoutInSeconds;
    }

    public int getShutdownTimeoutInSeconds() {
        return shutdownTimeoutInSeconds;
    }
}