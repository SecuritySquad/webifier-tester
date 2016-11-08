package de.securitysquad.webifier.test;

/**
 * Created by samuel on 07.11.16.
 */
public enum OutputFormat {
    CMD {
        @Override
        public String format(Object output) {
            // TODO customize
            return String.valueOf(output);
        }
    }, JSON {
        @Override
        public String format(Object output) {
            // TODO customize
            return String.valueOf(output) + "\nOutputFormat is JSON.";
        }
    }, XML {
        @Override
        public String format(Object output) {
            // TODO customize
            return String.valueOf(output) + "\nOutputFormat is XML.";
        }
    };

    // TODO create Object for output
    public abstract String format(Object output);

    // TODO create Object for output
    public void print(Object output) {
        System.out.println(format(output));
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