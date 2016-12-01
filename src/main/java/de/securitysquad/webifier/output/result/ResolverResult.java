package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 01.12.16.
 */
public class ResolverResult {
    @JsonProperty("original")
    private String originalUrl;
    @JsonProperty("resolved")
    private String resolvedUrl;
    @JsonProperty("reachable")
    private boolean reachable;

    public ResolverResult() {
        // needed for jackson
    }

    private ResolverResult(String originalUrl, String resolvedUrl, boolean reachable) {
        this.originalUrl = originalUrl;
        this.resolvedUrl = resolvedUrl;
        this.reachable = reachable;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getResolvedUrl() {
        return resolvedUrl;
    }

    public boolean isReachable() {
        return reachable;
    }

    public static ResolverResult errorResult(String originalUrl) {
        return new ResolverResult(originalUrl, null, false);
    }
}
