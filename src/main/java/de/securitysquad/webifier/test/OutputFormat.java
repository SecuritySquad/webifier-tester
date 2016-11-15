package de.securitysquad.webifier.test;

import de.securitysquad.webifier.output.message.Message;

/**
 * Created by samuel on 07.11.16.
 */
public enum OutputFormat {
    CMD, JSON, XML;

    public void print(Message message) {
        if (message == null) {
            throw new NullPointerException("message must not be null!");
        }
        System.out.println(message.formatMessage(this));
    }

    public static OutputFormat valueOfOrDefault(String name) {
        if (name == null) {
            return CMD;
        }
        try {
            return valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CMD;
        }
    }
}