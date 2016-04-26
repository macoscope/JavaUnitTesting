package com.macoscope.unittesting.goodies

import com.macoscope.unittesting.login.LoginCredentials
import com.macoscope.unittesting.login.LoginUseCase
import spock.lang.Ignore
import spock.lang.Specification

public class ExceptionSpec extends Specification {


    def 'exception type check'() {
        given:
            LoginUseCase sut = new LoginUseCase()
        when:
            sut.loginWithCredentials(null)
        then:
            thrown NullPointerException
    }

    def 'exception content check'() {
        given:
            LoginUseCase sut = new LoginUseCase()
        when:
            sut.loginWithCredentials(null)
        then:
            NullPointerException npe = thrown()
            npe.message == "Credentials cannot be null"
    }

    //XXX not recommended
    def 'exceptions type check by negation'() {
        given:
            LoginUseCase sut = new LoginUseCase()
        when:
            sut.loginWithCredentials(new LoginCredentials("", ""))
        then:
            notThrown NullPointerException
    }

    //XXX misuse, do not do this at home
    @Ignore("remove @Ignore and see results")
    def 'exceptions only from when block'() {
        given:
            LoginUseCase sut = new LoginUseCase()
            sut.loginWithCredentials(null)
        when:
            sut.loginWithCredentials(new LoginCredentials("", ""))
        then:
            NullPointerException npe = thrown()
            npe.message == "Credentials cannot be null"
    }
}
