# A Wrapper for Apache Commons CLI

See: http://commons.apache.org/proper/commons-cli/index.html

## Background

This wrapper is provided because the OptionBuilder class in commons-cli
calls static methods via an instance variable. Causing Intellij's code
inspections to light up.

The wrapper provides an OptionBuilder whoes Fluent interface does not 
upset Intellij.

