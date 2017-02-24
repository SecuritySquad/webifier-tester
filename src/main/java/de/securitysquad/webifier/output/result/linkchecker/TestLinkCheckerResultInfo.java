package de.securitysquad.webifier.output.result.linkchecker;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.securitysquad.webifier.output.result.TestResultInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 15.11.16.
 */
public class TestLinkCheckerResultInfo extends TestResultInfo {
    @JsonProperty("hosts")
    private List<TestLinkCheckerResultHost> hosts;

    public TestLinkCheckerResultInfo() {
        hosts = new ArrayList<>();
    }


    public List<TestLinkCheckerResultHost> getHosts() {
        return hosts;
    }
}