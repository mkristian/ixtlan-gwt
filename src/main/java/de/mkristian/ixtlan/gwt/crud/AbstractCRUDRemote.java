package de.mkristian.ixtlan.gwt.crud;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.singleton.AbstractSingletonRemote;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;


public abstract class AbstractCRUDRemote<T extends Identifiable> 
        extends AbstractSingletonRemote<T>
        implements CRUDRemote<T> {

    protected final CRUDFactory<T, ?> factory;
    
    protected AbstractCRUDRemote( EventBus eventBus,
                              NetworkIndicator networkIndicator,
                              CRUDFactory<T, ?> factory ) {
        super( eventBus, networkIndicator, factory );
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

    public void fireDelete( Method method, T model ){
        networkIndicator.finished();
        eventBus.fireEvent( factory.newEvent( method, model, Action.DESTROY ) );
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