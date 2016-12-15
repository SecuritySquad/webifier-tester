package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.securitysquad.webifier.output.result.headerinspection.HeaderInspectionResultInfo;

//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = TestVirusScanResultInfo.class, name = "TestVirusScanResultInfo"),
//        @JsonSubTypes.Type(value = HeaderInspectionResultInfo.class, name = "HeaderInspectionResultInfo"),
//})
public abstract class TestResultInfo {
}
