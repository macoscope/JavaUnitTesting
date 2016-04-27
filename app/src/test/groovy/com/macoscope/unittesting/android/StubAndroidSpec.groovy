package com.macoscope.unittesting.android

import android.location.Location
import android.text.TextUtils
import spock.lang.Ignore
import spock.lang.Specification

public class StubAndroidSpec extends Specification {

    //TODO add unitTests.returnDefaultValues = true to build.gradle
    @Ignore("remove @Ignore and see results")
    def "testing android with defaults"() {
        given:
            Location location = new Location("");
        expect:
            location.getTime() == 0
    }

    @Ignore("remove @Ignore and see results")
    def "testing android with stubs"() {
        given:
            Location location = Stub(Location);
            def time = System.currentTimeMillis()
            location.getTime() >> time
        expect:
            location.getTime() == time
    }

    @Ignore("remove @Ignore and see results")
    def "testing android static"() {
        expect:
            TextUtils.isEmpty("") == true
    }
}