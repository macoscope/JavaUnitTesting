package com.macoscope.unittesting.intro

import com.macoscope.unittesting.login.LoginCredentials
import com.macoscope.unittesting.login.LoginUseCase
import spock.lang.Specification

public class CoverageUnitTest extends Specification {

    def 'fake test'() {
        when:
            new LoginUseCase().loginCoverage(new LoginCredentials("",""))
        then:
            true
    }

    //TODO test method LoginUseCase.loginCoverage
}