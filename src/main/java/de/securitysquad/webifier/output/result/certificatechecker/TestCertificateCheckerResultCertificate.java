package de.securitysquad.webifier.output.result.certificatechecker;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 23.02.17.
 */
public class TestCertificateCheckerResultCertificate {
    @JsonProperty("subject")
    private TestCertificateCheckerResultCertificateInfo subject;
    @JsonProperty("issuer")
    private TestCertificateCheckerResultCertificateInfo issuer;
    @JsonProperty("validity")
    private TestCertificateCheckerResultCertificateValidity validity;
    @JsonProperty("return_code")
    private String returnCode;

    public TestCertificateCheckerResultCertificateInfo getSubject() {
        return subject;
    }

    public TestCertificateCheckerResultCertificateInfo getIssuer() {
        return issuer;
    }

    public TestCertificateCheckerResultCertificateValidity getValidity() {
        return validity;
    }

    public String getReturnCode() {
        return returnCode;
    }
}
