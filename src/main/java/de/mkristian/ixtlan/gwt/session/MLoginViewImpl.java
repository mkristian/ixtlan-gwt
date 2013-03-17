package de.mkristian.ixtlan.gwt.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

@Singleton
public class MLoginViewImpl extends AbstractLoginViewImpl {

    @UiTemplate("MLoginView.ui.xml")
    interface MLoginViewUiBinder extends UiBinder<Widget,MLoginViewImpl> {}

    @UiField
    public Button loginButton;

    @UiField
    public Button resetPasswordButton;

    @UiField
    public MTextBox username;

    @UiField
    public MTextBox login;

    @UiField
    public MPasswordTextBox password;

    @SuppressWarnings("unchecked")
    @Inject
    public MLoginViewImpl(){
        this( (UiBinder<Widget, MLoginViewImpl>) GWT.create(MLoginViewUiBinder.class) );
    }
    
    public MLoginViewImpl(UiBinder<Widget, MLoginViewImpl> uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("loginButton")
    public void onTapLogin(TapEvent e) {
        login();
    }

    @UiHandler("resetPasswordButton")
    public void onTapResetPassword(TapEvent e) {
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
