package com.macoscope.unittesting.android

import android.location.Location
import org.robospock.RoboSpecification

public class RoboSpockSpec extends RoboSpecification {


    def "testing android with RoboSpock"() {
        given:
            Location location = new Location("");
        expect:
            location.getTime() > System.currentTimeMillis() - 1000
    }
}