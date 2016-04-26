package com.macoscope.unittesting.basics

import com.macoscope.unittesting.login.LoginCredentials
import com.macoscope.unittesting.login.LoginService
import com.macoscope.unittesting.login.LoginUseCase
import spock.lang.Specification

public class MockSpec extends Specification {

    def 'test with real dependency - login success'() {
        given:
            LoginUseCase sut = new LoginUseCase(new LoginService())
        expect:
            sut.loginWithCredentialsWithStatus(credentials("john", "correct")) == true
    }

    def 'test with real dependency - login failure'() {
        given:
            LoginUseCase sut = new LoginUseCase(new LoginService())
        expect:
            sut.loginWithCredentialsWithStatus(credentials("any", "any")) == false
    }

    def 'mocking'() {
        given:
            LoginService loginServiceMock = Mock(LoginService)
            LoginUseCase sut = new LoginUseCase(loginServiceMock)
        when:
            sut.loginWithCredentials(credentials("john", "correct"))
        then:
            1 * loginServiceMock.login("john", "correct")
    }

    def 'mocking - any argument of type'() {
        given:
            LoginService loginServiceMock = Mock(LoginService)
            LoginUseCase sut = new LoginUseCase(loginServiceMock)
        when:
            sut.loginWithCredentials(credentials("any", "any"))
        then:
            1 * loginServiceMock.login(_ as String, _ as String)
    }

    //XXX not recommended, doesn't check anything
    def 'mocking - zero or any interactions'() {
        given:
            LoginService loginServiceMock = Mock(LoginService)
            LoginUseCase sut = new LoginUseCase(loginServiceMock)
        when:
            sut.loginWithCredentials(credentials("any", "any"))
        then:
            _ * loginServiceMock.login(_ as String, _ as String)
    }

    def 'mocking - at least one call'() {
        given:
            LoginService loginServiceMock = Mock(LoginService)
            LoginUseCase sut = new LoginUseCase(loginServiceMock)
        when:
            sut.loginWithCredentials(credentials("john", "correct"))
//            sut.loginWithCredentials(credentials("john", "correct"))
        then:
            (1.._) * loginServiceMock.login("john", "correct")
    }

    //XXX not recommended, focused on internal algorithm, only use when you really need 0 interactions
    def 'mocking - zero interactions'() {
        given:
            LoginService loginServiceMock = Mock(LoginService)
            LoginUseCase sut = new LoginUseCase(loginServiceMock)
        when:
            sut.loginWithCredentials(null)
        then:
            0 * loginServiceMock.login(_ as String, _ as String)
            thrown NullPointerException
    }


    private static LoginCredentials credentials(String login, String password) {
        LoginCredentials.builder().login(login).password(password).build()
    }
}