package de.securitysquad.webifier.launch;

import de.securitysquad.webifier.config.WebifierTestData;
import de.securitysquad.webifier.output.message.ResolverFinished;
import de.securitysquad.webifier.output.message.ResolverStarted;
import de.securitysquad.webifier.output.result.ResolverResult;
import de.securitysquad.webifier.test.OutputFormat;
import de.securitysquad.webifier.test.WebifierTest;
import de.securitysquad.webifier.test.WebifierTestListener;

import java.io.IOException;

import static de.securitysquad.webifier.output.result.ResolverResult.errorResult;

/**
 * Created by samuel on 01.12.16.
 */
public class WebifierResolver implements WebifierTestListener<ResolverResult> {
    private final String id;
    private final String url;
    private final OutputFormat outputFormat;
    private final WebifierTest<ResolverResult> test;

    public WebifierResolver(String id, String url, OutputFormat outputFormat, WebifierTestData resolverData) throws IOException {
        this.id = id;
        this.url = url;
        this.outputFormat = outputFormat;
        this.test = new WebifierTest<>(id, url, resolverData, this);
    }

    public void launch() {
        test.launch();
    }

    public ResolverResult waitForResult() throws InterruptedException {
        while (!test.isFinished()) {
            Thread.sleep(10);
        }
        ResolverResult result = test.getResult();
        if (result != null) {
            return result;
        }
        return errorResult(url);
    }

    @Override
    public void onTestStarted(WebifierTest test) {
        outputFormat.print(new ResolverStarted(id, test.getId(), url));
    }

    @Override
    public void onTestFinished(WebifierTest test, ResolverResult result) {
        outputFormat.print(new ResolverFinished(id, test.getId(), result));
    }

    @Override
    public void onTestError(WebifierTest test, Exception exception, ResolverResult result) {
        outputFormat.print(new ResolverFinished(id, test.getId(), errorResult(url)));
    }

    @Override
    public Class<ResolverResult> getResultClass() {
        return ResolverResult.class;
    }
}
