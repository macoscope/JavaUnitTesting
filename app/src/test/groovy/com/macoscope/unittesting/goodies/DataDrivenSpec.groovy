package com.macoscope.unittesting.goodies

import com.macoscope.unittesting.login.LoginCredentials
import com.macoscope.unittesting.login.LoginUseCase
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

public class DataDrivenSpec extends Specification {


    def 'data driven test'() {
        given:
            LoginUseCase sut = new LoginUseCase()
        when:
            sut.loginWithCredentials(new LoginCredentials(login, password))
        then:
            sut.loggedIn == loginStatus
        where:
            login  | password  || loginStatus
            "john" | "correct" || true
            "john" | "wrong"   || false
            "ANY"  | "correct" || false
            ""     | ""        || false
    }

//    @Unroll
    @Ignore("remove @Ignore and see results")
    def 'data driven test failure'() {
        given:
            LoginUseCase sut = new LoginUseCase()
        when:
            sut.loginWithCredentials(new LoginCredentials(login, password))
        then:
            sut.loggedIn == loginStatus
        where:
            login  | password  || loginStatus
            "john" | "correct" || false //fails
            "john" | "wrong"   || false
            "ANY"  | "correct" || true  //fails
    }

    @Unroll
    @Ignore("remove @Ignore and see results")
    def 'user with login: #login.toLowerCase() and password: #password has login status: #loginStatus'() {
        given:
            LoginUseCase sut = new LoginUseCase()
        when:
            sut.loginWithCredentials(new LoginCredentials(login, password))
        then:
            sut.loggedIn == loginStatus
        where:
            login  | password  || loginStatus
            "john" | "correct" || false //fails
            "john" | "wrong"   || false
            "ANY"  | "correct" || true  //fails
    }
}
