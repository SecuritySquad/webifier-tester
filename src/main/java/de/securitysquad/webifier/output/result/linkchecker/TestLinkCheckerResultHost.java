package de.securitysquad.webifier.output.result.linkchecker;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.WebifierResultType;

/**
 * Created by samuel on 23.02.17.
 */
public class TestLinkCheckerResultHost {
    @JsonProperty("host")
    private String host;
    @JsonProperty("result")
    private WebifierResultType result;

    public String getHost() {
        return host;
    }

    public WebifierResultType getResult() {
        return result;
    }
}
