package de.securitysquad.webifier.output.message.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import de.securitysquad.webifier.output.result.TestResult;
import de.securitysquad.webifier.output.result.TestVirusScanResult;
import de.securitysquad.webifier.output.result.VirusScanResult;

/**
 * Created by samuel on 09.11.16.
 */
public class TestFinishedWithResult extends TestMessage {
    @JsonProperty
    @JacksonXmlProperty
    private final TestResult result;

    public TestFinishedWithResult(String testerId, String testId, String testName, TestResult result) {
        super(testerId, testId, testName, "Test '" + testName + "' finished!");
        this.result = result;
    }

    @Override
    public String formatCmd() {
        String basic = super.formatCmd() + " Result: \nThe given url is " + (result.isMalicious() ? "" : "not ") + "malicious!";
        if (result instanceof TestVirusScanResult) {
            VirusScanResult info = ((TestVirusScanResult) result).getInfo();
            basic += "\nScanned Files: " + info.getScannedFiles() + " / Malicious Files: " + info.getMaliciousFiles();
        }
        return basic;
    }
}