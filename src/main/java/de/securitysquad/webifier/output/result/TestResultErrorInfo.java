package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by samuel on 22.12.16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class TestResultErrorInfo extends TestResultInfo {
    @JsonIgnore
    private Exception exception;

    public TestResultErrorInfo() {
        // needed for jackson
    }

    public TestResultErrorInfo(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}