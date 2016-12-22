package de.securitysquad.webifier.output.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import de.securitysquad.webifier.output.result.WebifierResultType;

/**
 * Created by samuel on 09.11.16.
 */
public class TesterFinished extends Message {
    @JsonProperty
    @JacksonXmlProperty
    private WebifierResultType result;

    public TesterFinished(String testerId, String url, WebifierResultType result) {
        super(testerId, "Tester finished for url " + url);
        this.result = result;
    }

    @Override
    protected String formatCmd() {
        return super.formatCmd() + "\nThe url is " + result.toString().toLowerCase() + "!";
    }
}
