package de.securitysquad.webifier.output.message.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import de.securitysquad.webifier.output.message.Message;

/**
 * Created by samuel on 09.11.16.
 */
public class TestMessage extends Message {
    @JsonProperty
    @JacksonXmlProperty
    private String test_id;
    @JsonProperty
    @JacksonXmlProperty
    private String test_name;

    public TestMessage(String testerId, String testId, String testName, String message) {
        super(testerId, message);
        this.test_id = testId;
        this.test_name = testName;
    }
}
