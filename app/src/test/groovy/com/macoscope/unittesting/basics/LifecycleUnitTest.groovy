package com.macoscope.unittesting.basics

import groovy.transform.TypeChecked
import spock.lang.Specification
import timber.log.Timber

@TypeChecked
public class LifecycleUnitTest extends Specification {

    void setupSpec() {
        Timber.plant(new StandardOutTree())
        Timber.d("setupSpec")
    }

    void setup() {
        Timber.d("setup")
    }

    def 'test first'() {
        given:
            Timber.d("test first");
        expect:
            true
    }

    def 'test second'() {
        given:
            Timber.d("test second");
        expect:
            true
    }

    void cleanup() {
        Timber.d("cleanup")
    }

    void cleanupSpec() {
        Timber.d("cleanupSpec")
    }



    class StandardOutTree extends Timber.DebugTree {
            @Override
            protected void log(int priority, String tag, String message, Throwable t) {
                System.out.print(message + "\n\n")
            }
    }
}