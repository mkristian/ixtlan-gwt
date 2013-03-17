package de.mkristian.ixtlan.gwt.session;

import javax.inject.Inject;

import com.google.gwt.inject.client.AbstractGinModule;

public class LoginGinModule extends AbstractGinModule {
        
    @Override
    public void configure(){
        bind(LoginPresenter.class).to(LoginPresenterImpl.class);
        bind(LoginViewWireup.class).asEagerSingleton();
    }

    public static class LoginViewWireup  {
        @Inject
        LoginViewWireup( SessionGWTApplication app, LoginView view, LoginPresenter presenter ){
            view.setPresenter( presenter );
            app.setPresenter( presenter );
        }
    }
}




