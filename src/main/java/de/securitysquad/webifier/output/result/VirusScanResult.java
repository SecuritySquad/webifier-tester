package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 15.11.16.
 */
public class VirusScanResult {
    @JsonProperty("scanned_files")
    private int scannedFiles;
    @JsonProperty("malicious_files")
    private int maliciousFiles;
    @JsonProperty("files")
    private List<VirusScanResultFile> files;

    public VirusScanResult() {
        files = new ArrayList<>();
    }

    public int getScannedFiles() {
        return scannedFiles;
    }

    public int getMaliciousFiles() {
        return maliciousFiles;
    }

    public List<VirusScanResultFile> getFiles() {
        return files;
    }
}