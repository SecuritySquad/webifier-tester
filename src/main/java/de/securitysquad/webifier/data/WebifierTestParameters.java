package de.securitysquad.webifier.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTestParameters {
    @JsonProperty
    private String name;
    @JsonProperty
    private String startup;
    @JsonProperty
    private String shutdown;
    @JsonProperty
    private boolean enabled;
    @JsonProperty
    private int weight;
    @JsonProperty
    private int startupTimeoutInSeconds;
    @JsonProperty
    private int shutdownTimeoutInSeconds;

    public WebifierTestParameters(String name, String startup, String shutdown, boolean enabled, int weight, int startupTimeoutInSeconds, int shutdownTimeoutInSeconds) {
        this.name = name;
        this.startup = startup;
        this.shutdown = shutdown;
        this.enabled = enabled;
        this.weight = weight;
        this.startupTimeoutInSeconds = startupTimeoutInSeconds;
        this.shutdownTimeoutInSeconds = shutdownTimeoutInSeconds;
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

    public int getWeight() {
        return weight;
    }

    public int getStartupTimeoutInSeconds() {
        return startupTimeoutInSeconds;
    }

    public int getShutdownTimeoutInSeconds() {
        return shutdownTimeoutInSeconds;
    }
}