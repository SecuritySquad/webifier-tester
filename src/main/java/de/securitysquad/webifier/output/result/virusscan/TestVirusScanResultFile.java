package de.securitysquad.webifier.output.result.virusscan;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.WebifierResultType;

/**
 * Created by samuel on 15.11.16.
 */
public class TestVirusScanResultFile {
    @JsonProperty("name")
    private String name;
    @JsonProperty("result")
    private WebifierResultType result;

    public String getName() {
        return name;
    }

    public WebifierResultType getResult() {
        return result;
    }
}
