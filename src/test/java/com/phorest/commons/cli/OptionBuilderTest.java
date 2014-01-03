/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (C) 2011-2013 Phorest, Inc. All rights reserved.
 *
 * http://www.phorest.com/
 *
 */
package com.phorest.commons.cli;

import org.apache.commons.cli.Option;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class OptionBuilderTest {
    @org.junit.Test
    public void builderMethodsShouldReturnRealOptionBuilderInstance() throws Exception {
        assertThat(OptionBuilder.withName("foo"), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.withShortName("foo"), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.withArgName("foo"), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.withDescription("foo"), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.withType(String.class), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.withValueSeparator(':'), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.isRequired(), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.isRequired(true), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.hasArg(), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.hasArg(true), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.hasArgs(), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.hasArgs(2), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.hasOptionalArg(), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.hasOptionalArgs(), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
        assertThat(OptionBuilder.hasOptionalArgs(2), is(instanceOf(OptionBuilder.RealOptionBuilder.class)));
    }

    @Test(expected = IllegalStateException.class)
    public void builderShouldNotCreateAnOptionWithoutName() throws Exception {
        OptionBuilder.withShortName("foo").create();
    }

    @Test
    public void builderShouldCreateAValidOption() throws Exception {
        final Option option = OptionBuilder.withName("longName").create();
        assertThat(option.getLongOpt(), is(equalTo("longName")));
        assertThat(option.getArgName(), is(equalTo("LONGNAME")));
        assertThat(option.isRequired(), is(false));
        assertThat(option.getDescription(), is(nullValue()));
        assertThat(option.getValue(), is(nullValue()));
    }

    @Test
    public void builderShouldCreateAValidOptionWithShortName() throws Exception {
        final Option option = OptionBuilder.withName("longName").withShortName("shortName").create();
        assertThat(option.getLongOpt(), is(equalTo("longName")));
        assertThat(option.getOpt(), is(equalTo("shortName")));
        assertThat(option.getArgName(), is(equalTo("LONGNAME")));
        assertThat(option.isRequired(), is(false));
        assertThat(option.getDescription(), is(nullValue()));
        assertThat(option.getValue(), is(nullValue()));
    }

    @Test
    public void builderShouldCreateAnOptionWithArg() throws Exception {
        final Option option = OptionBuilder
                .withName("longName")
                .withDescription("description")
                .isRequired()
                .hasArg()
                .create();
        assertThat(option.getLongOpt(), is(equalTo("longName")));
        assertThat(option.isRequired(), is(true));
        assertThat(option.getDescription(), is(equalTo("description")));
        assertThat(option.hasArg(), is(true));
    }

    @Test
    public void builderShouldCreateAnOptionWithArgs() throws Exception {
        final Option option = OptionBuilder
                .withName("longName")
                .hasArgs()
                .create();
        assertThat(option.getLongOpt(), is(equalTo("longName")));
        assertThat(option.hasArgs(), is(true));
    }

    @Test
    public void builderShouldCreateAnOptionWithOptionalArg() throws Exception {
        final Option option = OptionBuilder
                .withName("longName")
                .withArgName("NAME")
                .withValueSeparator(':')
                .hasOptionalArg()
                .create();
        assertThat(option.hasOptionalArg(), is(true));
        assertThat(option.getArgName(), is(equalTo("NAME")));
        assertThat(option.getValueSeparator(), is(equalTo(':')));
    }

    @Test
    public void builderShouldCreateAnOptionWithOptionalArgs() throws Exception {
        final Option option = OptionBuilder
                .withName("longName")
                .hasOptionalArgs()
                .create();
        assertThat(option.hasOptionalArg(), is(true));
    }
}
