package com.macoscope.unittesting.basics

import com.macoscope.unittesting.login.model.LoginCredentials
import com.macoscope.unittesting.login.model.LoginService
import com.macoscope.unittesting.login.model.LoginUseCase
import spock.lang.Specification

public class NamingSpec extends Specification {

    def 'should login user with correct credentials'() {
        given:
            LoginUseCase sut = new LoginUseCase(new LoginService())
            LoginCredentials correctLoginCredentials =
                    LoginCredentials.builder().login("john").password("correct").build()
        when:
            sut.loginWithCredentials(correctLoginCredentials)
        then:
            sut.isLoggedIn()
    }

    def 'logs in user with correct credentials'() {
        given:
            LoginUseCase sut = new LoginUseCase(new LoginService())
            LoginCredentials correctLoginCredentials =
                    LoginCredentials.builder().login("john").password("correct").build()
        when:
            sut.loginWithCredentials(correctLoginCredentials)
        then:
            sut.isLoggedIn()
    }

    def 'signs in user with correct credentials'() {
        given: 'correct user credentials'
            LoginUseCase sut = new LoginUseCase(new LoginService())
            LoginCredentials correctLoginCredentials =
                    LoginCredentials.builder().login("john").password("correct").build()
        when: 'user logs in with correct credentials'
            sut.loginWithCredentials(correctLoginCredentials)
        then: 'user is logged in'
            sut.isLoggedIn()
    }
}