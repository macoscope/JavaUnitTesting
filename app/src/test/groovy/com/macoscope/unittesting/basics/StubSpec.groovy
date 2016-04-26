package com.macoscope.unittesting.basics

import com.macoscope.unittesting.login.LoginCredentials
import com.macoscope.unittesting.login.LoginService
import com.macoscope.unittesting.login.LoginUseCase
import spock.lang.Specification

public class StubSpec extends Specification {


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

    def 'stubbing'() {
        given:
            LoginService loginServiceStub = Stub(LoginService)
            loginServiceStub.login("stubLogin", "stubPassword") >> true
            LoginUseCase sut = new LoginUseCase(loginServiceStub)
        expect:
            sut.loginWithCredentialsWithStatus(credentials("stubLogin", "stubPassword")) == true
    }

    def 'stubbing - any argument of type'() {
        given:
            LoginService loginServiceStub = Stub(LoginService)
            loginServiceStub.login(_ as String, _ as String) >> true
            LoginUseCase sut = new LoginUseCase(loginServiceStub)
        expect:
            sut.loginWithCredentialsWithStatus(credentials("any", "any")) == true
    }

    private static LoginCredentials credentials(String login, String password) {
        LoginCredentials.builder().login(login).password(password).build()
    }
}