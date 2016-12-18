package de.securitysquad.webifier.output.message.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import de.securitysquad.webifier.output.message.ErrorMessage;

/**
 * Created by samuel on 09.11.16.
 */
public class TestErrorMessage extends ErrorMessage {
    @JsonProperty
    @JacksonXmlProperty
    private String test_id;
    @JsonProperty
    @JacksonXmlProperty
    private String test_name;

    public TestErrorMessage(String testerId, String testId, String testName, Exception exception) {
        super(testerId, "An error occurred in Test " + testName+ "!", exception);
        this.test_id = testId;
        this.test_name = testName;
    }
}
