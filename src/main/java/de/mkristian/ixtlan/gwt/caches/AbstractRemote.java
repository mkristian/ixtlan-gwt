package de.mkristian.ixtlan.gwt.caches;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.Factory;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;


public abstract class AbstractRemote<T extends Identifiable> 
            implements Remote<T> {

    protected final EventBus eventBus;
    protected final NetworkIndicator networkIndicator;
    protected final Factory<T, ?> factory;
    
    protected AbstractRemote(EventBus eventBus,
                    NetworkIndicator networkIndicator,
                    Factory<T, ?> factory ) {
        this.eventBus = eventBus;
        this.networkIndicator = networkIndicator;
        this.factory = factory;
    }
    
    public void fireCreate( Method method, T model ){
        networkIndicator.finished();
        eventBus.fireEvent( factory.newEvent( method, model, Action.CREATE ) );
    }

    public void fireRetrieve( Method method, List<T> models ){
        networkIndicator.finished();
        eventBus.fireEvent( factory.newEvent( method, models, Action.LOAD ) );
    }

    public void fireRetrieve( Method method, T model ){
        networkIndicator.finished();
        eventBus.fireEvent( factory.newEvent( method, model, Action.LOAD ) );
    }

    public void fireUpdate( Method method, T model ){
        networkIndicator.finished();
        eventBus.fireEvent( factory.newEvent( method, model, Action.UPDATE ) );
    }

    public void fireDelete( Method method, T model ){
        networkIndicator.finished();
        GWT.log( "Abstraactremote" + model );
        eventBus.fireEvent( factory.newEvent( method, model, Action.DESTROY ) );
    }

    public void fireError( Method method, Throwable e ){
        networkIndicator.finished();
        eventBus.fireEvent( factory.newEvent( method, e ) );
    }

    protected MethodCallback<T> newCreateCallback() {
        return new MethodCallback<T>() {

            public void onSuccess(Method method, T response) {
                fireCreate(method, response);
            }

            public void onFailure(Method method, Throwable exception) {
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
    
    protected MethodCallback<Void> newDeleteCallback( final T model ) {
        return new MethodCallback<Void>() {

            public void onSuccess( Method method, Void response ) {
                fireDelete( method, model );
            }

            public void onFailure( Method method, Throwable exception ) {
                fireError(method, exception);
            }
        };
    }

	@Override
    public void update( T model ) {
        networkIndicator.saving();
    }

    @Override
    public void delete( T model ) {
        networkIndicator.deleting();
    }

	@Override
	public void retrieve( int id ) {
        networkIndicator.loading();		
	}

	@Override
	public void retrieveAll() {
        networkIndicator.loading();		
	}

	@Override
	public void create( T model ) {
        networkIndicator.saving();		
	}
}