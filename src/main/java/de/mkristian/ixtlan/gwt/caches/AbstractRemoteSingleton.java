package de.mkristian.ixtlan.gwt.caches;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;

public abstract class AbstractRemoteSingleton<T> 
            implements RemoteSingleton<T> {

    protected final EventBus eventBus;
    protected final NetworkIndicator networkIndicator;
    
    protected AbstractRemoteSingleton(EventBus eventBus,
            NetworkIndicator networkIndicator){
        this.eventBus = eventBus;
        this.networkIndicator = networkIndicator;
    }
    
    public void fireRetrieve(Method method, T model){
        networkIndicator.finished();
        eventBus.fireEvent(newEvent(method, model, Action.LOAD));
    }
    
    public void fireUpdate(Method method, T model){
        networkIndicator.finished();
        eventBus.fireEvent(newEvent(method, model, Action.UPDATE));
    }
    
    public void fireError(Method method, Throwable e){
        networkIndicator.finished();
        eventBus.fireEvent(newEvent(method, e));
    }
    
    protected MethodCallback<T> newRetrieveCallback() {
        return new MethodCallback<T>() {

            public void onSuccess(Method method, T response) {
                fireRetrieve(method, response);
            }

            public void onFailure(Method method, Throwable exception) {
                // TODO maybe propagate the exception or do nothing
                fireError(method, exception);
            }
        };
    }
    
    protected MethodCallback<T> newUpdateCallback() {
        return new MethodCallback<T>() {

            public void onSuccess(Method method, T response) {
                fireUpdate(method, response);
            }

            public void onFailure(Method method, Throwable exception) {
                // TODO maybe propagate the exception or do nothing
                fireError(method, exception);
            }
        };
    }
    
    abstract protected ModelEvent<T> newEvent(Method method, T model, Action action);
    abstract protected ModelEvent<T> newEvent(Method method, Throwable e);
}