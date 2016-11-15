package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 15.11.16.
 */
public class VirusScanResultFile {
    @JsonProperty("name")
    private String name;
    @JsonProperty("malicious")
    private boolean malicious;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMalicious() {
        return malicious;
    }

    public void setMalicious(boolean malicious) {
        this.malicious = malicious;
    }
}
