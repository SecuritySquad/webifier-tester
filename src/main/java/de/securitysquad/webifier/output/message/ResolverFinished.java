package de.securitysquad.webifier.output.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import de.securitysquad.webifier.output.message.test.TestMessage;
import de.securitysquad.webifier.output.result.ResolverResult;

/**
 * Created by samuel on 01.12.16.
 */
public class ResolverFinished extends TestMessage {
    @JsonProperty
    @JacksonXmlProperty
    private final ResolverResult result;

    public ResolverFinished(String testerId, String testId, ResolverResult result) {
        super(testerId, testId, "resolver", "Resolver finished!");
        this.result = result;
    }

    @Override
    public String formatCmd() {
        return super.formatCmd() + " Result: \nThe resolved url is '" + result.getResolvedUrl() + "' and it is " + (result.isReachable() ? "" : "not ") + "reachable.";
    }
}
