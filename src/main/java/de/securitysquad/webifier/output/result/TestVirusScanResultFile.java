package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 15.11.16.
 */
public class TestVirusScanResultFile {
    @JsonProperty("name")
    private String name;
    @JsonProperty("malicious")
    private boolean malicious;

    public String getName() {
        return name;
    }

    public boolean isMalicious() {
        return malicious;
    }
}
