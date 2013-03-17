package de.mkristian.ixtlan.gwt.session;

import de.mkristian.ixtlan.gwt.utils.GWTApplication;


public interface SessionGWTApplication extends GWTApplication{
    
    void startSession( User user );
    
    void stopSession();

    void setPresenter(LoginPresenter presenter);

}