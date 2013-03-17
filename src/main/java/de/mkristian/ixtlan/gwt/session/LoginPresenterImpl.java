package de.mkristian.ixtlan.gwt.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.GWT;

import de.mkristian.ixtlan.gwt.utils.DisplayMessage;

@Singleton
public class LoginPresenterImpl implements LoginPresenter{

    private final SessionRemote remote;
    private final SessionRestService service;
    private final DisplayMessage displayMassage;
    private final SessionGWTApplication app;

    @Inject
    public LoginPresenterImpl( final SessionRestService service,
            final DisplayMessage displayMessage, 
            final SessionGWTApplication app,
            final SessionRemote remote ) {
        this.service = service;
        this.app = app;
        this.displayMassage = displayMessage;
        this.remote = remote;
    }

    @Override
    public void login(final String login, final String password) {
        remote.loading();
        Authentication authentication = new Authentication(login, password);
        service.create(authentication, new MethodCallback<Session>() {

            @Override
            public void onSuccess(Method method, Session session) {
                GWT.log("logged in: " + login);
                remote.fireCreate( method, session );
                app.startSession( session.user );
            }

            @Override
            public void onFailure(Method method, Throwable exception) {
                GWT.log("login failed: " + exception.getMessage(), exception);
                remote.fireError( method, exception );
                displayMassage.error( "access denied" );
            }
        });
    }

    @Override
    public void logout() {
        remote.loading();
        app.stopSession();
        service.destroy(new MethodCallback<Void>() {
            @Override
            public void onSuccess(Method method, Void response) {
                remote.fireDelete(method, null);  
            } 
            @Override
            public void onFailure(Method method, Throwable exception) {
                remote.fireDelete(method, null);   
            } 
        });
      }

    @Override
    public void resetPassword(final String login) {
        remote.loading();
        Authentication authentication = new Authentication(login);
        service.resetPassword(authentication, new MethodCallback<Void>() {

            @Override
            public void onSuccess(Method method, Void result) {
                remote.finished();
                displayMassage.info("new password was sent to your email address");
            }

            @Override
            public void onFailure(Method method, Throwable exception) {
                remote.finished();
                displayMassage.error("could not reset password - username/email unknown");
            }
        });
    }
}