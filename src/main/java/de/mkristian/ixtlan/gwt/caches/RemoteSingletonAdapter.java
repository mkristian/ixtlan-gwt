package de.mkristian.ixtlan.gwt.caches;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.places.SingletonFactory;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;

public abstract class RemoteSingletonAdapter<T> 
        extends AbstractRemoteSingleton<T>{

    protected RemoteSingletonAdapter( EventBus eventBus,
                    NetworkIndicator networkIndicator,
                    SingletonFactory<T, ?> factory ) {
        super( eventBus, networkIndicator, factory );
    }

}