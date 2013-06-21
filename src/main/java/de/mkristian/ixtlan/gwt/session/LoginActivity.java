package de.mkristian.ixtlan.gwt.session;

import javax.inject.Inject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class LoginActivity extends MGWTAbstractActivity {

    protected final LoginView view;

    @Inject
    public LoginActivity( LoginView view ) {
        this.view = view;
    }

    public void start(AcceptsOneWidget display, EventBus eventBus) {
        display.setWidget( view.asWidget() );
    }

}