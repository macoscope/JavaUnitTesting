package com.macoscope.unittesting.goodies

import spock.lang.Ignore
import spock.lang.Specification

@Ignore("remove @Ignore and see results")
public class GroovyAssertionsSpec extends Specification {


    def "assert calculation"() {
        expect:
            2 + 2 * 2 == 16 / 2
    }

    def "assert string"() {
        when:
            String text = "Java is awesome!"
        then:
            text == "Groovy is awesome!!!"
    }

    def "assert lists"() {
        when:
            def before = ["Piotr", "Tomasz"]
            def after = ["Piotr", "Tomasz", "Dariusz"]

        then:
            before == after
    }
}