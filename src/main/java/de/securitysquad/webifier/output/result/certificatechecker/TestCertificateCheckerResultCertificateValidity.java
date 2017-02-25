package de.securitysquad.webifier.output.result.certificatechecker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by samuel on 25.02.17.
 */
public class TestCertificateCheckerResultCertificateValidity {
    @JsonProperty("from")
    private Date from;
    @JsonProperty("to")
    private Date to;

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }
}