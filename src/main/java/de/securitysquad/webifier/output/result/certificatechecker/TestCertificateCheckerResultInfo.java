package de.securitysquad.webifier.output.result.certificatechecker;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResultInfo;

/**
 * Created by samuel on 15.11.16.
 */
public class TestCertificateCheckerResultInfo extends TestResultInfo {
    @JsonProperty("certificate")
    private TestCertificateCheckerResultCertificate certificate;

    public TestCertificateCheckerResultCertificate getCertificate() {
        return certificate;
    }
}