package de.securitysquad.webifier.output.result.virusscan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 15.11.16.
 */
public class TestVirusScanResultInfo {
    @JsonProperty("scanned_files")
    private int scannedFiles;
    @JsonProperty("malicious_files")
    private int maliciousFiles;
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

    public List<TestVirusScanResultFile> getFiles() {
        return files;
    }
}