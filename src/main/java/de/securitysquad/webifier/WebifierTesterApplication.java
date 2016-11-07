package de.securitysquad.webifier;

import org.apache.commons.cli.*;

import java.net.MalformedURLException;

import static org.apache.commons.cli.Option.builder;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTesterApplication {

    public static final String HELP = "h";
    public static final String URL = "u";
    public static final String ASYNCHRONOUS = "a";

    public static void main(String[] args) throws MalformedURLException, ParseException {
        Options options = new Options();
        options.addOption(builder(HELP).longOpt("help").desc("Print this help screen.").build());
        options.addOption(builder(URL).longOpt("url").hasArg().valueSeparator().desc("The url that should be tested.").argName("URL").build());
        options.addOption(builder(ASYNCHRONOUS).longOpt("asynchronous").desc("Print the result of a single test when its done.").build());
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption(HELP) || !cmd.hasOption(URL)) {
            new HelpFormatter().printHelp("java -jar webifier-tester.jar", options);
            return;
        }
        boolean asynchronous = cmd.hasOption(ASYNCHRONOUS);
        new WebifierTester(cmd.getOptionValue(URL), asynchronous).launch();
    }
}