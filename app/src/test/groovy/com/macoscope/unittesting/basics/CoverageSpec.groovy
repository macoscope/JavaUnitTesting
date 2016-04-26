package com.macoscope.unittesting.basics

import com.macoscope.unittesting.login.LoginCredentials
import com.macoscope.unittesting.login.LoginUseCase
import spock.lang.Ignore
import spock.lang.Specification

public class CoverageSpec extends Specification {

    @Ignore("cheating")
    def 'fake test'() {
        when:
            new LoginUseCase().loginCoverage(new LoginCredentials("",""))
        then:
            true
    }

    //TODO test method LoginUseCase.loginCoverage
}