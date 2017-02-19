package de.securitysquad.webifier.test;

import de.securitysquad.webifier.output.result.WebifierResultType;

public class WebifierOverallTestResult {
    private final WebifierResultType resultType;
    private final double resultValue;

    public WebifierOverallTestResult(WebifierResultType resultType) {
        this(resultType, -1);
    }

    public WebifierOverallTestResult(WebifierResultType resultType, double resultValue) {
        this.resultType = resultType;
        this.resultValue = resultValue;
    }

    public WebifierResultType getResultType() {
        return resultType;
    }

    public double getResultValue() {
        return resultValue;
    }
}