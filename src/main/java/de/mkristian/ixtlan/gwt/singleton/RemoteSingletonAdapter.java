package de.mkristian.ixtlan.gwt.singleton;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.singleton.AbstractSingletonRemote;
import de.mkristian.ixtlan.gwt.singleton.SingletonFactory;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;

public abstract class RemoteSingletonAdapter<T> 
        extends AbstractSingletonRemote<T>{

    protected RemoteSingletonAdapter( EventBus eventBus,
                    NetworkIndicator networkIndicator,
                    SingletonFactory<T, ?> factory ) {
        super( eventBus, networkIndicator, factory );
    }

}