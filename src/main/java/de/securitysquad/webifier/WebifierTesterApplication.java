package de.securitysquad.webifier;

import de.securitysquad.webifier.config.WebifierConfig;
import de.securitysquad.webifier.config.WebifierConfigLoader;
import de.securitysquad.webifier.test.OutputFormat;
import de.securitysquad.webifier.test.WebifierTester;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.UUID;

import static de.securitysquad.webifier.test.OutputFormat.valueOfOrDefault;
import static org.apache.commons.cli.Option.builder;

/**
 * Created by samuel on 07.11.16.
 */
public class WebifierTesterApplication {
    private static final String HELP = "h";
    private static final String URL = "u";
    private static final String OUTPUT = "o";
    private static final String ID = "i";

    public static void main(String[] args) {
        new WebifierTesterApplication(args);
    }

    private WebifierTesterApplication(String... args) {
        Options options = new Options();
        options.addOption(builder(HELP).longOpt("help").desc("Print this help screen.").build());
        options.addOption(builder(URL).longOpt("url").hasArg().valueSeparator().desc("The url that should be tested.").argName("URL").build());
        options.addOption(builder(OUTPUT).longOpt("output").hasArg().valueSeparator().desc("Set the format of the output. Valid formats are JSON and XML.").argName("FORMAT").build());
        options.addOption(builder(ID).longOpt("id").hasArg().valueSeparator().desc("Set the id for this test").argName("ID").build());
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption(HELP) || !cmd.hasOption(URL)) {
                printHelp(options);
                return;
            }
            launchTester(cmd);
        } catch (ParseException | IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            printHelp(options);
        }
    }

    private void launchTester(CommandLine cmd) throws IOException {
        String id = UUID.randomUUID().toString();
        if (cmd.hasOption(ID)) {
            id = cmd.getOptionValue(ID);
        }
        OutputFormat outputFormat = OutputFormat.CMD;
        if (cmd.hasOption(OUTPUT)) {
            outputFormat = valueOfOrDefault(cmd.getOptionValue(OUTPUT));
        }
        WebifierConfigLoader loader = new WebifierConfigLoader();
        WebifierConfig config = loader.load(ClassLoader.getSystemResourceAsStream("config.json"));
        new WebifierTester(cmd.getOptionValue(URL), id, outputFormat, config.getTests()).launch();
    }

    private void printHelp(Options options) {
        new HelpFormatter().printHelp("java -jar webifier-tester.jar", options);
    }
}