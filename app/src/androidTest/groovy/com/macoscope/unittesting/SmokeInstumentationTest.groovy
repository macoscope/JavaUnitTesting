package com.macoscope.unittesting

import android.app.Application;
import android.test.ApplicationTestCase;

public class SmokeInstumentationTest extends ApplicationTestCase<Application> {
    public SmokeInstumentationTest() {
        super(Application.class)
    }
}