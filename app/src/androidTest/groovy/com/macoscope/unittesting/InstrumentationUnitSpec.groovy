package com.macoscope.unittesting

import android.location.Location
import spock.lang.Specification

public class InstrumentationUnitSpec extends Specification {

    def "testing android with android instrumentation"() {
        given:
            Location location = new Location("");
        expect:
            location.getTime() == 0
    }
}