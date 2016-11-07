package de.securitysquad.webifier;

import org.apache.commons.cli.*;

import java.net.MalformedURLException;

import static de.securitysquad.webifier.OutputFormat.valueOfOrDefault;
import static java.util.Collections.emptyList;
import static org.apache.commons.cli.Option.builder;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTesterApplication {
    private static final String HELP = "h";
    private static final String URL = "u";
    private static final String OUTPUT = "o";

    public static void main(String[] args) throws MalformedURLException, ParseException {
        Options options = new Options();
        options.addOption(builder(HELP).longOpt("help").desc("Print this help screen.").build());
        options.addOption(builder(URL).longOpt("url").hasArg().valueSeparator().desc("The url that should be tested.").argName("URL").build());
        options.addOption(builder(OUTPUT).longOpt("output").hasArg().valueSeparator().desc("Set the format of the output. Valid formats are JSON and XML.").argName("FORMAT").build());
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption(HELP) || !cmd.hasOption(URL)) {
            new HelpFormatter().printHelp("java -jar webifier-tester.jar", options);
            return;
        }
        OutputFormat outputFormat = OutputFormat.CMD;
        if (cmd.hasOption(OUTPUT)) {
            outputFormat = valueOfOrDefault(cmd.getOptionValue(OUTPUT));
        }
        // TODO load test list from config file
        new WebifierTester(cmd.getOptionValue(URL), outputFormat, emptyList()).launch();
    }
}