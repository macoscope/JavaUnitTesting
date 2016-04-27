package com.macoscope.unittesting.goodies

import spock.lang.AutoCleanup
import spock.lang.IgnoreRest
import spock.lang.Specification
import spock.lang.Timeout

public class ExtensionsSpec extends Specification {

    //@AutoCleanup(value = "closeException", quiet = false)
    @AutoCleanup
    //@Shared
    DatabaseConnection databaseConnection = new DatabaseConnection()

    def "auto cleanup first"() {
        expect:
            databaseConnection.open()
            true
    }

    def "auto cleanup second"() {
        expect:
            databaseConnection.open()
            true
    }

    @IgnoreRest
    @Timeout(1)
    def "timeout"() {
        expect:
            sleep 500
//            sleep 1000
            true
    }

    class DatabaseConnection {

        void open() {
            System.out.println("open");
        }

        void close() {
            System.out.println("close");
        }

        void closeException() {
            System.out.println("closeException");
            throw new IllegalStateException("Connection already closed")
        }
    }
}