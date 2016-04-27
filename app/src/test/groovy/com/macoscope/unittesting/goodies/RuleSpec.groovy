package com.macoscope.unittesting.goodies

import org.junit.ClassRule
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import spock.lang.Shared
import spock.lang.Specification

public class RuleSpec extends Specification {

    @Shared
    @ClassRule
    LoggingRule loggingRuleClass = new LoggingRule()

    @Rule
    LoggingRule loggingRuleTest = new LoggingRule()

    def "first test"() {

        expect:
            sleep 1000
            true
    }

    def "second test"() {

        expect:
            sleep 500
            true
    }

    class LoggingRule implements TestRule {
        @Override
        public Statement apply(final Statement base, final Description description) {
            return new Statement() {
                @Override
                void evaluate() throws Throwable {
                    def testName = description.displayName
                    System.out.print testName + " - Started\n\n"
                    base.evaluate();
                    System.out.print testName + " - Finished\n\n"
                }
            };
        }
    }
}