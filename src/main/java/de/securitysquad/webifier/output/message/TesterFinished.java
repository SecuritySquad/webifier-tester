package de.securitysquad.webifier.output.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by samuel on 09.11.16.
 */
public class TesterFinished extends Message {
    @JsonProperty
    @JacksonXmlProperty
    private boolean malicious;

    public TesterFinished(String testerId, String url, boolean malicious) {
        super(testerId, "Tester finished for url " + url);
        this.malicious = malicious;
    }

    @Override
    protected String formatCmd() {
        return super.formatCmd() + "\nThe url is " + (malicious ? "" : "not ") + "malicious";
    }
}
