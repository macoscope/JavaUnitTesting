package com.macoscope.unittesting.intro

import com.macoscope.unittesting.login.LoginCredentials
import com.macoscope.unittesting.login.LoginService
import com.macoscope.unittesting.login.LoginUseCase
import spock.lang.Specification

public class CoverageUnitTest extends Specification {

    def 'fake test'() {
        given:
            LoginUseCase loginUseCase = new LoginUseCase(new LoginService())
        when:
            loginUseCase.loginCoverage(new LoginCredentials("",""))
        then:
            true
    }

    //TODO test method LoginUseCase.loginCoverage
}