package de.mkristian.ixtlan.gwt.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;

@Singleton
public class SessionRemote {

    private final EventBus eventBus;
    private final NetworkIndicator networkIndicator;
    
    @Inject
    protected SessionRemote(EventBus eventBus,
            NetworkIndicator networkIndicator ){
        this.eventBus = eventBus;
        this.networkIndicator = networkIndicator;
    }
    
    public void loading(){
        networkIndicator.loading();
    }
    
    public void finished(){
        networkIndicator.finished();
    }
    
    public void fireCreate(Method method, Session model){
        networkIndicator.finished();
        eventBus.fireEvent(new SessionEvent(method, model, Action.CREATE));
    }

    public void fireRetrieve(Method method, Session model){
        networkIndicator.finished();
        eventBus.fireEvent(new SessionEvent(method, model, Action.LOAD));
    }

    public void fireDelete(Method method, Session model){
        networkIndicator.finished();
        eventBus.fireEvent(new SessionEvent(method, model, Action.DESTROY));
    }

    public void fireError(Method method, Throwable e){
        networkIndicator.finished();
        eventBus.fireEvent(new SessionEvent(method, e));
    }
}