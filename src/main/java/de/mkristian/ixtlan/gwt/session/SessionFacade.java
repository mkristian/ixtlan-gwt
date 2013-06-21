/**
 *
 */
package de.mkristian.ixtlan.gwt.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.events.ModelEvent;

@Singleton
public class SessionFacade{

    private Session session;
    private final LoginPresenter presenter;

    @Inject
    public SessionFacade( EventBus eventBus, LoginPresenter presenter ){
        this.presenter = presenter;
        eventBus.addHandler( SessionEvent.TYPE, new SessionEventHandler() {
            
            @Override
            public void onModelEvent(ModelEvent<Session> event) {
                switch( event.getAction() ){
                    case CREATE:
                        session = event.getModel();
                        break;
                    case DESTROY:
                        session = null;
                        break;
                    default:
                }
            }
        });
    }
    
    public User user(){
        return session.user;
    }
    
    public boolean isActive(){
        return session != null;
    }
    
    public void destroy(){
        presenter.logout();
    }
}
