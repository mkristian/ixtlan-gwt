package de.mkristian.ixtlan.gwt.caches;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;


public abstract class AbstractRemote<T extends Identifiable> 
            implements Remote<T> {

    protected final EventBus eventBus;
    protected final NetworkIndicator networkIndicator;
    
    protected AbstractRemote(EventBus eventBus,
                    NetworkIndicator networkIndicator){
        this.eventBus = eventBus;
        this.networkIndicator = networkIndicator;
    }
    
    public void fireCreate(Method method, T model){
        networkIndicator.finished();
        eventBus.fireEvent(newEvent(method, model, Action.CREATE));
    }

    public void fireRetrieve(Method method, List<T> models){
        networkIndicator.finished();
        eventBus.fireEvent(newEvent(method, models, Action.LOAD));
    }

    public void fireRetrieve(Method method, T model){
        networkIndicator.finished();
        eventBus.fireEvent(newEvent(method, model, Action.LOAD));
    }

    public void fireUpdate(Method method, T model){
        networkIndicator.finished();
        eventBus.fireEvent(newEvent(method, model, Action.UPDATE));
    }

    public void fireDelete(Method method, T model){
        networkIndicator.finished();
        eventBus.fireEvent(newEvent(method, model, Action.DESTROY));
    }

    public void fireError(Method method, Throwable e){
        networkIndicator.finished();
        eventBus.fireEvent(newEvent(method, e));
    }

    protected MethodCallback<T> newCreateCallback() {
        return new MethodCallback<T>() {

            public void onSuccess(Method method, T response) {
                fireCreate(method, response);
            }

            public void onFailure(Method method, Throwable exception) {
                // TODO maybe propagate the exception or do nothing
                fireError(method, exception);
            }
        };
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

    protected MethodCallback<List<T>> newRetrieveAllCallback() {
        return new MethodCallback<List<T>>() {

            public void onSuccess(Method method, List<T> response) {
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
    
    protected MethodCallback<Void> newDeleteCallback( final T model ) {
        return new MethodCallback<Void>() {

            public void onSuccess( Method method, Void response ) {
                fireDelete( method, model );
            }

            public void onFailure( Method method, Throwable exception ) {
                // TODO maybe propagate the exception or do nothing
                fireError(method, exception);
            }
        };
    }

    abstract protected ModelEvent<T> newEvent(Method method, List<T> models, Action action);
    abstract protected ModelEvent<T> newEvent(Method method, T model, Action action);
    abstract protected ModelEvent<T> newEvent(Method method, Throwable e);
}