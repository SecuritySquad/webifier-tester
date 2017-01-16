package de.securitysquad.webifier.output.result.virusscan;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResultInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 15.11.16.
 */
public class TestVirusScanResultInfo extends TestResultInfo {
    @JsonProperty("scanned_files")
    private int scannedFiles;
    @JsonProperty("malicious_files")
    private int maliciousFiles;
    @JsonProperty("suspicious_files")
    private int suspiciousFiles;
    @JsonProperty("files")
    private List<TestVirusScanResultFile> files;

    public TestVirusScanResultInfo() {
        files = new ArrayList<>();
    }

    public int getScannedFiles() {
        return scannedFiles;
    }

    public int getMaliciousFiles() {
        return maliciousFiles;
    }

    public int getSuspiciousFiles() {
        return suspiciousFiles;
    }

    public List<TestVirusScanResultFile> getFiles() {
        return files;
    }
}