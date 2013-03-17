package de.mkristian.ixtlan.gwt.caches;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;

public abstract class RemoteReadOnlyAdapter<T extends Identifiable> 
            extends AbstractRemoteReadOnly<T>{

    protected RemoteReadOnlyAdapter( EventBus eventBus,
                    NetworkIndicator networkIndicator ) {
        super( eventBus, networkIndicator );
    }

    public void retrieve(int id) {
        throw new RuntimeException( "not implemented" );
    }

    public void retrieveAll() {
        throw new RuntimeException( "not implemented" );
    }
}