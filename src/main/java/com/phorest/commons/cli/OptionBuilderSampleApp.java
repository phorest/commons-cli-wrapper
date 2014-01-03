/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (C) 2014 Phorest, Inc. All rights reserved.
 *
 * http://www.phorest.com/
 *
 */
package com.phorest.commons.cli;

import org.apache.commons.cli.ParseException;

import static com.phorest.commons.cli.OptionBuilder.withName;

public class OptionBuilderSampleApp {
    public static void main(String[] args) {
        final OptionHandler optionHandler = new OptionHandler();
        optionHandler
                .add(withName("region").hasArg().isRequired())
                .add(withName("domain").hasArg());

        try {
            optionHandler.parse(args);

            System.out.println("region = " + optionHandler.getOptionValue("region"));

            if (optionHandler.hasOption("domain")) {
                System.out.println("domain value: " + optionHandler.getOptionValue("domain"));
            }
        } catch (ParseException e) {
            optionHandler.errorOrHelp("OptionBuilderSampleApp --region [--domain DOMAIN]", e.getMessage());
        }
    }
}
