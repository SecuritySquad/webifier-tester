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

    public void setScannedFiles(int scannedFiles) {
        this.scannedFiles = scannedFiles;
    }

    public int getMaliciousFiles() {
        return maliciousFiles;
    }

    public void setMaliciousFiles(int maliciousFiles) {
        this.maliciousFiles = maliciousFiles;
    }

    public List<VirusScanResultFile> getFiles() {
        return files;
    }

    public void setFiles(List<VirusScanResultFile> files) {
        this.files = files;
    }
}