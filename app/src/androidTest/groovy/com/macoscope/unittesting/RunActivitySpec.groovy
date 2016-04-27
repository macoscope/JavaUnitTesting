package com.macoscope.unittesting

import android.support.test.rule.ActivityTestRule
import com.macoscope.unittesting.login.view.LoginActivity
import org.junit.Rule
import spock.lang.Specification

public class RunActivitySpec extends Specification {

    @Rule
    ActivityTestRule<LoginActivity> diagramActivityRule = new ActivityTestRule(LoginActivity)

    def "testing android UI"() {
        expect:
            diagramActivityRule.getActivity() instanceof LoginActivity
    }
}