package de.mkristian.ixtlan.gwt.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

@Singleton
public class LoginViewImpl extends AbstractLoginViewImpl {

    @UiTemplate("LoginView.ui.xml")
    interface LoginViewUiBinder extends UiBinder<Widget, LoginViewImpl> {}

    @UiField
    public Button loginButton;

    @UiField
    public Button resetPasswordButton;

    @UiField
    public TextBox username;

    @UiField
    public TextBox login;

    @UiField
    public PasswordTextBox password;

    @SuppressWarnings("unchecked")
    @Inject
    public LoginViewImpl(){
        this( (UiBinder<Widget, LoginViewImpl>) GWT.create(LoginViewUiBinder.class) );
    }
    
    public LoginViewImpl(UiBinder<Widget, LoginViewImpl> uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("loginButton")
    public void onClickLogin(ClickEvent e) {
        login();
    }

    @UiHandler("resetPasswordButton")
    public void onClickResetPassword(ClickEvent e) {
        resetPassword();
    }
    
    @Override
    HasValue<String> getLogin() {
        return login;
    }

    @Override
    HasValue<String> getUsername() {
        return username;
    }
    
    @Override
    HasValue<String> getPassword() {
        return password;
    }
}
