/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (C) 2011-2013 Phorest, Inc. All rights reserved.
 *
 * http://www.phorest.com/
 *
 */
package com.phorest.commons.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import static com.phorest.commons.cli.OptionBuilder.withName;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class OptionHandlerTest {
    private OptionHandler optionHandler;

    @Before
    public void setUp() throws Exception {
        optionHandler = new OptionHandler();
    }

    @Test
    public void handlerShouldAcceptAnOption() throws Exception {
        optionHandler.add(withName("domain").create());
        CommandLineParser parser = new GnuParser();
        final CommandLine cl = parser.parse(optionHandler.getOptions(), new String[]{"--domain"});
        assertThat(cl.hasOption("domain"), is(true));
    }

    @Test
    public void handlerShouldAcceptAnOptionBuilder() throws Exception {
        optionHandler.add(withName("domain"));
        CommandLineParser parser = new GnuParser();
        final CommandLine cl = parser.parse(optionHandler.getOptions(), new String[]{"--domain"});
        assertThat(cl.hasOption("domain"), is(true));
    }

    @Test
    public void handlerShouldParseOptions() throws Exception {
        optionHandler.add(withName("domain").create());
        optionHandler.parse(new String[]{"--domain"});
        final CommandLine cl = optionHandler.getCommandLine();
        assertThat(cl.hasOption("domain"), is(true));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldParseBeforeGettingCommandLine() throws Exception {
        optionHandler.getCommandLine();
    }

    @Test(expected = OptionHandler.HelpRequestedException.class)
    public void helpOptionShouldThrowParseException() throws Exception {
        optionHandler.add(withName("domain").create());
        try {
            optionHandler.parse(new String[]{"--help"});
        } catch (ParseException e) {
            assertThat(optionHandler.isHelpRequested(), is(true));
            throw e;
        }
    }

    @Test
    public void settingOptionShouldMakeValueAvailable() throws Exception {
        optionHandler.add(withName("domain").hasArg());
        optionHandler.parse(new String[]{"--domain", "eu-west-1"});
        assertThat(optionHandler.isHelpRequested(), is(false));
        assertThat(optionHandler.hasOption("domain"), is(true));
        assertThat(optionHandler.getOptionValue("domain"), is(equalTo("eu-west-1")));
    }

    @Test(expected = IllegalStateException.class)
    public void requestingOptionBeforeParseShouldFail() throws Exception {
        optionHandler.add(withName("domain").hasArg());
        assertThat(optionHandler.getOptionValue("unknown"), is(nullValue()));
    }

    @Test
    public void requestingInvalidOptionValueShouldNotGiveValue() throws Exception {
        optionHandler.add(withName("domain").hasArg());
        optionHandler.parse(new String[] {});
        assertThat(optionHandler.getOptionValue("unknown"), is(nullValue()));
    }

    @Test
    public void parseShouldAcceptNull() throws Exception {
        optionHandler.add(withName("domain").hasArg());
        optionHandler.parse(null);
        assertThat(optionHandler.getOptionValue("domain"), is(nullValue()));
    }

    @Test
    public void errorMessageShouldContainUsage() throws Exception {
        optionHandler.add(withName("domain").hasArg().withDescription("help text"));
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        optionHandler.error(new PrintWriter(baos), "syntax", "message");
        assertThat(baos.toString(), containsString("help text"));
    }

    @Test
    public void xx() throws Exception {
        optionHandler
                .add(withName("domain").hasArg())
                .add(withName("region"))
                .add(withName("command"))
        ;
        optionHandler.parse(new String[]{
                "--domain", "eu-west-1"
        });
    }
}
