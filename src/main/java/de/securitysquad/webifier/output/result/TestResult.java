package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 09.11.16.
 */
public class TestResult<T extends TestResultInfo> implements Cloneable {
    @JsonProperty("result")
    private WebifierResultType resultType;
    @JsonProperty("info")
    private T info;

    public TestResult() {
        // needed for jackson
    }

    private TestResult(WebifierResultType resultType, T info) {
        this.resultType = resultType;
        this.info = info;
    }

    public WebifierResultType getResultType() {
        return resultType;
    }

    public T getInfo() {
        return info;
    }

    @Override
    public TestResult<T> clone() {
        try {
            return (TestResult<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static TestResult<TestResultErrorInfo> undefinedResult(Exception exception) {
        return new TestResult<>(WebifierResultType.UNDEFINED, new TestResultErrorInfo(exception));
    }
}