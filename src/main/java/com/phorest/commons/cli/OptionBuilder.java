/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package com.phorest.commons.cli;

import org.apache.commons.cli.Option;

/**
 * This is a replacement for org.apache.commons.cli.OptionBuilder the use of which
 * results in static methods being accessed via an instance reverence. So, OptionBuilder
 * methods return an instance of OptionBuilder.RealOptionBuilder whose methods can be
 * chained without warning.
 */
public class OptionBuilder {

    private OptionBuilder() {
        // do nothing
    }

    /**
     * The option created by RealOptionBuilder will have the following long name.
     *
     * @param name the option long name
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder withName(String name) {
        return new RealOptionBuilder().withName(name);
    }

    /**
     * The next Option created will have the following long option value.
     *
     * @param shortName the long option value
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder withShortName(String shortName) {
        return new RealOptionBuilder().withShortName(shortName);
    }

    /**
     * The next Option created will require an argument value.
     *
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder hasArg() {
        return new RealOptionBuilder().hasArg();
    }

    /**
     * The next Option created will require an argument value if
     * <code>hasArg</code> is true.
     *
     * @param hasArg if true then the Option has an argument value
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder hasArg(boolean hasArg) {
        return new RealOptionBuilder().hasArg(hasArg);
    }

    /**
     * The next Option created will have the specified argument value name.
     *
     * @param name the name for the argument value
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder withArgName(String name) {
        return new RealOptionBuilder().withArgName(name);
    }

    /**
     * The next Option created will be required.
     *
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder isRequired() {
        return new RealOptionBuilder().isRequired();
    }

    /**
     * The next Option created uses <code>separator</code> as a means to
     * separate argument values.
     * <p/>
     * <b>Example:</b>
     * <pre>
     * Option opt = withValueSeparator(':').create('D');
     *
     * CommandLine line = parser.parse(args);
     * String propertyName = opt.getValue(0);
     * String propertyValue = opt.getValue(1);
     * </pre>
     *
     * @param separator The value separator to be used for the argument values.
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder withValueSeparator(char separator) {
        return new RealOptionBuilder().withValueSeparator(separator);
    }

    /**
     * The next Option created will be required if <code>required</code>
     * is true.
     *
     * @param optionRequiredSetting if true then the Option is required
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder isRequired(boolean optionRequiredSetting) {
        return new RealOptionBuilder().isRequired(optionRequiredSetting);
    }

    /**
     * The next Option created can have unlimited argument values.
     *
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder hasArgs() {
        return new RealOptionBuilder().hasArgs();
    }

    /**
     * The next Option created can have <code>numberOfArguments</code> argument values.
     *
     * @param numberOfArguments the number of args that the option can have
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder hasArgs(int numberOfArguments) {
        return new RealOptionBuilder().hasArgs(numberOfArguments);
    }

    /**
     * The next Option can have an optional argument.
     *
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder hasOptionalArg() {
        return new RealOptionBuilder().hasOptionalArg();
    }

    /**
     * The next Option can have an unlimited number of optional arguments.
     *
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder hasOptionalArgs() {
        return new RealOptionBuilder().hasOptionalArgs();
    }

    /**
     * The next Option can have the specified number of optional arguments.
     *
     * @param numberOfArguments - the maximum number of optional arguments
     *                          the next Option created can have.
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder hasOptionalArgs(int numberOfArguments) {
        return new RealOptionBuilder().hasOptionalArgs(numberOfArguments);
    }

    /**
     * The next Option created will have a value that will be an instance
     * of <code>type</code>.
     *
     * @param newType the type of the Options argument value
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder withType(Object newType) {
        return new RealOptionBuilder().withType(newType);
    }

    /**
     * The next Option created will have the specified description
     *
     * @param description a description of the Option's purpose
     * @return an instance of RealOptionBuilder whose methods can be chained.
     */
    public static RealOptionBuilder withDescription(String description) {
        return new RealOptionBuilder().withDescription(description);
    }

    public static class RealOptionBuilder {
        /**
         * long option
         */
        protected String optionName;

        /**
         * short option
         */
        protected String shortName;

        /**
         * option description
         */
        protected String description;

        /**
         * argument name
         */
        protected String argumentName;

        /**
         * is required?
         */
        protected boolean required;

        /**
         * the number of arguments
         */
        protected int numberOfArgs = Option.UNINITIALIZED;

        /**
         * option type
         */
        protected Object type;

        /**
         * option can have an optional argument value
         */
        protected boolean optionalArg;

        /**
         * value separator for argument value
         */
        protected char valueSeparator;

        /**
         * The next Option created will have the following long option name.
         *
         * @param optionName the long option value
         * @return an instance of RealOptionBuilder whose methods can be chained.
         */
        public RealOptionBuilder withName(String optionName) {
            this.optionName = optionName;
            return this;
        }

        /**
         * The next Option created will have the following short option name.
         *
         * @param shortName the long option value
         * @return an instance of RealOptionBuilder whose methods can be chained.
         */
        public RealOptionBuilder withShortName(String shortName) {
            this.shortName = shortName;
            return this;
        }

        /**
         * The next Option created will require an argument value.
         *
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder hasArg() {
            return hasArg(true);
        }

        /**
         * The next Option created will require an argument value if
         * <code>hasArg</code> is true.
         *
         * @param hasArg if true then the Option has an argument value
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder hasArg(boolean hasArg) {
            numberOfArgs = hasArg ? 1 : Option.UNINITIALIZED;
            return this;
        }

        /**
         * The next Option created will have the specified argument value name.
         *
         * @param name the name for the argument value
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder withArgName(String name) {
            argumentName = name;
            return this;
        }

        /**
         * The next Option created will be required.
         *
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder isRequired() {
            return isRequired(true);
        }

        /**
         * The next Option created uses <code>sep</code> as a means to
         * separate argument values.
         * <p/>
         * <b>Example:</b>
         * <pre>
         * Option opt = withValueSeparator(':')
         *                           .create('D');
         *
         * CommandLine line = parser.parse(args);
         * String propertyName = opt.getValue(0);
         * String propertyValue = opt.getValue(1);
         * </pre>
         *
         * @param sep The value separator to be used for the argument values.
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder withValueSeparator(char sep) {
            valueSeparator = sep;
            return this;
        }

        /**
         * The next Option created will be required if <code>required</code>
         * is true.
         *
         * @param newRequired if true then the Option is required
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder isRequired(boolean newRequired) {
            required = newRequired;
            return this;
        }

        /**
         * The next Option created can have unlimited argument values.
         *
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder hasArgs() {
            return hasArgs(Option.UNLIMITED_VALUES);
        }

        /**
         * The next Option created can have <code>num</code> argument values.
         *
         * @param num the number of args that the option can have
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder hasArgs(int num) {
            numberOfArgs = num;
            return this;
        }

        /**
         * The next Option can have an optional argument.
         *
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder hasOptionalArg() {
            return hasOptionalArgs(1);
        }

        /**
         * The next Option can have an unlimited number of optional arguments.
         *
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder hasOptionalArgs() {
            return hasOptionalArgs(Option.UNLIMITED_VALUES);
        }

        /**
         * The next Option can have the specified number of optional arguments.
         *
         * @param numArgs - the maximum number of optional arguments
         *                the next Option created can have.
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder hasOptionalArgs(int numArgs) {
            numberOfArgs = numArgs;
            optionalArg = true;
            return this;
        }

        /**
         * The next Option created will have a value that will be an instance
         * of <code>type</code>.
         *
         * @param newType the type of the Options argument value
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder withType(Object newType) {
            type = newType;
            return this;
        }

        /**
         * The next Option created will have the specified description
         *
         * @param newDescription a description of the Option's purpose
         * @return the RealOptionBuilder instance
         */
        public RealOptionBuilder withDescription(String newDescription) {
            description = newDescription;
            return this;
        }

        /**
         * Create an Option using the current settings
         *
         * @return the Option instance
         * @throws IllegalArgumentException if <code>longOpt</code> has not been set.
         */
        public Option create() {
            if (optionName == null) {
                throw new IllegalStateException("Option name not provided (must call withName())");
            }

            Option option = new Option(shortName, description);

            // set the option properties
            option.setLongOpt(optionName);
            option.setRequired(required);
            option.setOptionalArg(optionalArg);
            option.setArgs(numberOfArgs);
            option.setType(type);
            option.setValueSeparator(valueSeparator);
            option.setArgName(argumentName != null ? argumentName : optionName.toUpperCase());

            return option;
        }
    }
}
