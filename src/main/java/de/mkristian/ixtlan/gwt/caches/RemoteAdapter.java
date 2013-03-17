package de.mkristian.ixtlan.gwt.caches;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;


public abstract class RemoteAdapter<T extends Identifiable> 
            extends AbstractRemote<T>{

    protected RemoteAdapter( EventBus eventBus,
                              NetworkIndicator networkIndicator ) {
        super( eventBus, networkIndicator );
    }

    public T newModel() {
        throw new RuntimeException( "not implemented" );
    }

    public void create(T model) {
        throw new RuntimeException( "not implemented" );
    }

    public void retrieve(int id) {
        throw new RuntimeException( "not implemented" );
    }

    public void retrieveAll() {
        throw new RuntimeException( "not implemented" );
    }

    public void update(T model) {
        throw new RuntimeException( "not implemented" );
    }

    public void delete(T model) {
        throw new RuntimeException( "not implemented" );
    }        
}