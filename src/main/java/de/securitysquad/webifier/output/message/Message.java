package de.securitysquad.webifier.output.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import de.securitysquad.webifier.test.OutputFormat;

/**
 * Created by samuel on 09.11.16.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "typ")
public class Message {
    protected static final ObjectMapper jsonMapper = new ObjectMapper();
    protected static final XmlMapper xmlMapper = new XmlMapper();

    @JsonProperty
    @JacksonXmlProperty
    private final String message;
    @JsonProperty
    @JacksonXmlProperty
    private final String tester_id;

    public Message(String testerId, String message) {
        this.message = message;
        this.tester_id = testerId;
    }

    public String formatMessage(OutputFormat format) {
        switch (format) {
            case JSON:
                return formatJson();
            case XML:
                return formatXml();
            default:
                return formatCmd();
        }
    }

    protected String formatCmd() {
        return message;
    }

    protected String formatJson() {
        try {
            return jsonMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return new ErrorMessage(tester_id, e).formatMessage(OutputFormat.CMD);
        }
    }

    protected String formatXml() {
        try {
            return xmlMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return new ErrorMessage(tester_id, e).formatMessage(OutputFormat.CMD);
        }
    }
}