package de.securitysquad.webifier.output.result.certificatechecker;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 25.02.17.
 */
public class TestCertificateCheckerResultCertificateInfo {
    @JsonProperty("name")
    private String name;
    @JsonProperty("organisation")
    private String organisation;
    @JsonProperty("organisation_unit")
    private String organisationUnit;

    public String getName() {
        return name;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getOrganisationUnit() {
        return organisationUnit;
    }
}