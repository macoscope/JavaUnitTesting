package com.macoscope.unittesting.basics

import com.macoscope.unittesting.login.LoginCredentials
import com.macoscope.unittesting.login.LoginService
import com.macoscope.unittesting.login.LoginUseCase
import spock.lang.Specification

public class BasicSpec extends Specification {

    def 'given when then structure'() {
        given:
            LoginUseCase sut = new LoginUseCase(new LoginService())
            LoginCredentials correctLoginCredentials =
                    LoginCredentials.builder().login("john").password("correct").build()
        when:
            sut.loginWithCredentials(correctLoginCredentials)
        then:
            sut.isLoggedIn()
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