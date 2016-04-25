package com.macoscope.unittesting.login;

import android.support.annotation.VisibleForTesting;

import lombok.Getter;


public class LoginUseCase {

    LoginService loginService;
    @Getter
    boolean loggedIn = false;

    @VisibleForTesting
    public LoginUseCase(){
        this(new LoginService());
    }

    public LoginUseCase(LoginService loginService){
        this.loginService = loginService;
    }

    public void loginWithCredentials(LoginCredentials credentials) {
        loggedIn = loginService.login(credentials.login, credentials.password);
    }

    public boolean loginWithCredentialsWithStatus(LoginCredentials credentials) {
        loginWithCredentials(credentials);
        return loggedIn;
    }

    public boolean loginCoverage(LoginCredentials credentials) {
        return loginService.loginWithBranches(credentials.login, credentials.password);
    }
}
