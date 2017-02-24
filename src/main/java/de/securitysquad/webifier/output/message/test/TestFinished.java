package de.securitysquad.webifier.output.message.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import de.securitysquad.webifier.output.result.TestResult;
import de.securitysquad.webifier.output.result.WebifierResultType;
import de.securitysquad.webifier.output.result.virusscan.TestVirusScanResultInfo;

/**
 * Created by samuel on 09.11.16.
 */
public class TestFinished extends TestMessage {
    @JsonProperty
    @JacksonXmlProperty
    private final TestResult result;

    public TestFinished(String testerId, String testId, String testName, TestResult result) {
        super(testerId, testId, testName, "Test '" + testName + "' finished!");
        this.result = result;
    }

    @Override
    public String formatCmd() {
        String basic = super.formatCmd() + " Result: \n" + getResultMessage(result.getResultType());
        if (result.getInfo() instanceof TestVirusScanResultInfo) {
            TestVirusScanResultInfo info = (TestVirusScanResultInfo) result.getInfo();
            basic += "\nScanned Files: " + info.getScannedFiles() + " / Suspicious Files: " + info.getSuspiciousFiles() + " / Malicious Files: " + info.getMaliciousFiles();
        }
        return basic;
    }

    private String getResultMessage(WebifierResultType result) {
        switch (result) {
            case CLEAN:
                return "The given url is clean!";
            case SUSPICIOUS:
                return "The given url is suspicious!";
            case MALICIOUS:
                return "The given url is malicious!";
        }
        return "The test result is undefined. Maybe the test returned an error!";
    }
}