package de.mkristian.ixtlan.gwt.readonly;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.readonly.AbstractReadonlyRemote;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyFactory;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;

public abstract class ReadonlyRemoteAdapter<T extends Identifiable> 
            extends AbstractReadonlyRemote<T>{

    protected ReadonlyRemoteAdapter( EventBus eventBus,
                                     NetworkIndicator networkIndicator,
                                     ReadonlyFactory<T, ?> factory ) {
        super( eventBus, networkIndicator, factory );
    }

    public void retrieve(int id) {
        throw new RuntimeException( "not implemented" );
    }

    public void retrieveAll() {
        throw new RuntimeException( "not implemented" );
    }
}