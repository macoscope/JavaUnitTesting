package com.macoscope.unittesting.login;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginUseCase {

    final LoginService loginService;

    @Getter
    boolean loggedIn = false;

    public void loginWithCredentials(LoginCredentials credentials) {
        loggedIn = loginService.login(credentials.login, credentials.password);
    }

    public boolean loginWithCredentialsWithStatus(LoginCredentials credentials) {
        loginWithCredentials(credentials);
        return loggedIn;
    }
}
