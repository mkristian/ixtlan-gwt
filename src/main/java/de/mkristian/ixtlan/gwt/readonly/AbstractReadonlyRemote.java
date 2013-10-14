package de.mkristian.ixtlan.gwt.readonly;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;

public abstract class AbstractReadonlyRemote<T extends Identifiable> 
            implements ReadonlyRemote<T> {

    protected final EventBus eventBus;
    protected final NetworkIndicator networkIndicator;
    protected final ReadonlyFactory<T, ?> factory;
    
    protected AbstractReadonlyRemote( EventBus eventBus,
                                      NetworkIndicator networkIndicator,
                                      ReadonlyFactory<T, ?> factory ){
        this.eventBus = eventBus;
        this.networkIndicator = networkIndicator;
        this.factory = factory;
    }
    
    public void fireRetrieve(Method method, List<T> models){
        networkIndicator.finished();
        eventBus.fireEvent( factory.newEvent( method, models, Action.LOAD ) );
    }

    public void fireRetrieve(Method method, T model){
        networkIndicator.finished();
        eventBus.fireEvent( factory.newEvent( method, model, Action.LOAD ) );
    }

    public void fireError(Method method, Throwable e){
        networkIndicator.finished();
        eventBus.fireEvent( factory.newEvent( method, e ) );
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

    protected MethodCallback<List<T>> newRetrieveAllCallback() {
        return new MethodCallback<List<T>>() {

            public void onSuccess(Method method, List<T> response) {
                fireRetrieve(method, response);
            }

            public void onFailure(Method method, Throwable exception) {
                fireError(method, exception);
            }
        };
    }
    
    @Override
    public void retrieve( int id ) {
        networkIndicator.loading();     
    }

    @Override
    public void retrieveAll() {
        networkIndicator.loading();     
    }

}