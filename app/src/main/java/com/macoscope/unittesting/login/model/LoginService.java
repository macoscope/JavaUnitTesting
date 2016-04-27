package com.macoscope.unittesting.login.model;

import timber.log.Timber;

public class LoginService {

    public static final String LOGIN = "john";
    public static final String PASSWORD = "correct";

    public boolean login(String login, String password) {
        Timber.v("login %s, password %s", login, password);
        return LOGIN.equals(login) && PASSWORD.equals(password);
    }

    public boolean loginWithBranches(String login, String password) {
        Timber.v("login %s, password %s", login, password);
        if (LOGIN.equals(login)) {
            if (PASSWORD.equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
