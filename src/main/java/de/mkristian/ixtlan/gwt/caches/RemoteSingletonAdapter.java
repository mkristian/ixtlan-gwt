package de.mkristian.ixtlan.gwt.caches;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;

public abstract class RemoteSingletonAdapter<T> 
        extends AbstractRemoteSingleton<T>{

    protected RemoteSingletonAdapter( EventBus eventBus,
                    NetworkIndicator networkIndicator ) {
        super( eventBus, networkIndicator );
    }

    public void retrieve() {
        throw new RuntimeException( "not implemented" );
    }

    public void update(T model) {
        throw new RuntimeException( "not implemented" );
    }
}