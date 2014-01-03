/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (C) 2014 Phorest, Inc. All rights reserved.
 *
 * http://www.phorest.com/
 *
 */
package com.phorest.commons.cli;

import org.apache.commons.cli.*;

import java.io.OutputStream;
import java.io.PrintWriter;

public class OptionHandler {
    private static final String HELP_OPTION = "help";
    private final Options options;
    private final CommandLineParser parser;
    private CommandLine commandLine;
    private boolean helpRequested;

    public OptionHandler() {
        this.options = new Options();
        this.parser = new OptionParser();
        add(OptionBuilder.withName(HELP_OPTION).withDescription("print this help message"));
    }

    public OptionHandler add(Option option) {
        options.addOption(option);
        return this;
    }

    public Options getOptions() {
        return options;
    }

    public OptionHandler add(OptionBuilder.RealOptionBuilder builder) {
        return add(builder.create());
    }

    public CommandLine parse(String[] args) throws ParseException {
        return parse(args, false);
    }

    public CommandLine parse(String[] args, boolean stopAtNonOption) throws ParseException {
        commandLine = parser.parse(options, args, stopAtNonOption);
        return commandLine;
    }

    public CommandLine getCommandLine() {
        if (commandLine == null) {
            throw new IllegalStateException("Parse command line first.");
        }
        return commandLine;
    }

    public boolean isHelpRequested() {
        return helpRequested;
    }

    public void error(String commandSyntax) {
        error(commandSyntax, null);
    }

    public void error(String commandSyntax, String errorMessage) {
        error(System.out, commandSyntax, errorMessage);
    }

    public void error(OutputStream out, String commandSyntax, String errorMessage) {
        final PrintWriter pw = new PrintWriter(out);
        error(pw, commandSyntax, errorMessage);
        pw.flush();
    }

    public void error(PrintWriter pw, String commandSyntax, String errorMessage) {
        pw.println("Failed to parse command line.");
        if (errorMessage != null) {
            pw.println();
            pw.println(errorMessage);
        }
        pw.println();

        usage(pw, commandSyntax);
    }

    public void usage(String commandSyntax) {
        usage(System.out, commandSyntax);
    }

    private void usage(OutputStream out, String commandSyntax) {
        final PrintWriter pw = new PrintWriter(out);
        usage(pw, commandSyntax);
    }

    public void usage(PrintWriter pw, String commandSyntax) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(pw, 80, commandSyntax, "\nOptions:", options, 0, 0, null, false);
        pw.flush();
    }

    public void errorOrHelp(String commandSyntax, String errorMessage) {
        errorOrHelp(System.out, commandSyntax, errorMessage);
    }

    public void errorOrHelp(OutputStream out, String commandSyntax, String errorMessage) {
        final PrintWriter pw = new PrintWriter(out);
        errorOrHelp(pw, commandSyntax, errorMessage);
        pw.flush();
    }

    public void errorOrHelp(PrintWriter pw, String commandSyntax, String errorMessage) {
        if (isHelpRequested()) {
            usage(pw, commandSyntax);
        } else {
            error(pw, commandSyntax, errorMessage);
        }
    }

    public String getOptionValue(String name) {
        return getCommandLine().getOptionValue(name);
    }

    public boolean hasOption(String name) {
        return getCommandLine().hasOption(name);
    }

    private class OptionParser extends GnuParser {
        @Override
        protected void checkRequiredOptions() throws MissingOptionException {
            if (cmd.hasOption(HELP_OPTION)) {
                helpRequested = true;
                throw new HelpRequestedException();
            }
            super.checkRequiredOptions();
        }

    }

    public static class HelpRequestedException extends MissingOptionException {
        private HelpRequestedException() {
            super("--help was provided");
        }
    }
}
