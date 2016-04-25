package com.macoscope.unittesting.example

import com.macoscope.unittesting.login.LoginCredentials
import com.macoscope.unittesting.login.LoginService
import com.macoscope.unittesting.login.LoginUseCase
import spock.lang.Specification

public class BasicUnitTest extends Specification {

    def 'given when then structure'() {
        given:
            LoginUseCase loginUseCase = new LoginUseCase(new LoginService())
            LoginCredentials correctLoginCredentials =
                    LoginCredentials.builder().login("john").password("correct").build()
        when:
            loginUseCase.loginWithCredentials(correctLoginCredentials)
        then:
            loginUseCase.isLoggedIn()
    }

    def 'expect structure'() {
        given:
            LoginUseCase loginUseCase = new LoginUseCase(new LoginService())
            LoginCredentials correctLoginCredentials =
                    LoginCredentials.builder().login("john").password("correct").build()
        expect:
            loginUseCase.loginWithCredentialsWithStatus(correctLoginCredentials) == true
    }

}