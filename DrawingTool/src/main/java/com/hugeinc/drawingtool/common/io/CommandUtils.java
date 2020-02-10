package com.hugeinc.drawingtool.common.io;

import com.hugeinc.drawingtool.common.DrawingToolConstants;
import org.apache.commons.cli.*;

public class CommandUtils {

    public static Options getArgumentOptions() {
        final Options arguments = new Options();

        final Option input = new Option(DrawingToolConstants.ARG_OPTION_INPUT, true, DrawingToolConstants.ARG_OPTION_INPUT_DESCRIPTION);
        input.setRequired(true);
        arguments.addOption(input);

        final Option output = new Option(DrawingToolConstants.ARG_OPTION_OUTPUT, true, DrawingToolConstants.ARG_OPTION_OUTPUT_DESCRIPTION);
        output.setRequired(false);
        arguments.addOption(output);

        return arguments;
    }

    public static CommandLine getCommandLine(final String cmdLine, final Options options, final String[] args) {
        final CommandLineParser parser = new DefaultParser();
        final HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (final ParseException pe) {
            System.out.println(pe.getMessage());

            formatter.printHelp(cmdLine, options);
        }

        return cmd;
    }


}
