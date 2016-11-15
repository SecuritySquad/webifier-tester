package de.securitysquad.webifier.output.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Created by samuel on 09.11.16.
 */
public class ErrorMessage extends Message {
    @JsonProperty
    @JacksonXmlProperty
    private final Exception exception;

    public ErrorMessage(String testerId, Exception exception) {
        this(testerId, "An error occurred!", exception);
    }

    public ErrorMessage(String testerId, String message, Exception exception) {
        super(testerId, message);
        this.exception = exception;
    }

    @Override
    public String formatCmd() {
        return super.formatCmd() + "\n" + ExceptionUtils.getStackTrace(exception);
    }
}