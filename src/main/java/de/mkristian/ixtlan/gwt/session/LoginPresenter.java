package de.mkristian.ixtlan.gwt.session;

public interface LoginPresenter {

    void login(String login, String password);

    void resetPassword(String login);

    void logout();
}