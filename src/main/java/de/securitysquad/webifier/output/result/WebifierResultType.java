package de.securitysquad.webifier.output.result;

/**
 * Created by samuel on 13.12.16.
 */
public enum WebifierResultType {
    /**
     * CLEAN is returned if the test was successful and the tested page is clean
     */
    CLEAN,
    /**
     * SUSPICIOUS is returned if the test was successful but the tested page is probably malicious
     */
    SUSPICIOUS,
    /**
     * MALICIOUS is returned if the test was successful and the tested page is malicious
     */
    MALICIOUS,
    /**
     * UNDEFINED is returned if the test was not successful and runs in an error (e.g. s timeout)
     */
    UNDEFINED
}