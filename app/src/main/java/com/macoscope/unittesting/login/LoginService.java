package com.macoscope.unittesting.login;

import timber.log.Timber;

public class LoginService {

    public void login(String login, String password) {
        Timber.v("login %s, password %s", login, password);
    }
}
