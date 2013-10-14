package de.mkristian.ixtlan.gwt.singleton;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;

public abstract class AbstractSingletonRemote<T> 
            implements SingletonRemote<T> {

    protected final EventBus eventBus;
    protected final NetworkIndicator networkIndicator;
    protected final SingletonFactory<T, ?> factory;
    
    protected AbstractSingletonRemote(EventBus eventBus,
            NetworkIndicator networkIndicator,
            SingletonFactory<T, ?> factory ){
        this.eventBus = eventBus;
        this.networkIndicator = networkIndicator;
        this.factory = factory;
    }
    
    public void fireRetrieve(Method method, T model){
        networkIndicator.finished();
        eventBus.fireEvent(factory.newEvent(method, model, Action.LOAD));
    }
    
    public void fireUpdate(Method method, T model){
        networkIndicator.finished();
        eventBus.fireEvent(factory.newEvent(method, model, Action.UPDATE));
    }
    
    public void fireError(Method method, Throwable e){
        networkIndicator.finished();
        eventBus.fireEvent(factory.newEvent(method, e));
    }
    
    protected MethodCallback<T> newRetrieveCallback() {
        return new MethodCallback<T>() {

            public void onSuccess(Method method, T response) {
                fireRetrieve(method, response);
            }

            public void onFailure(Method method, Throwable exception) {
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
                fireError(method, exception);
            }
        };
    }
    
    public void retrieve() {
        networkIndicator.loading();
    }

    public void update(T model) {
        networkIndicator.saving();
    }
}