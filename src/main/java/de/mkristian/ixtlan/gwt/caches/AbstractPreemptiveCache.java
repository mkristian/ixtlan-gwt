package de.mkristian.ixtlan.gwt.caches;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.Factory;

public abstract class AbstractPreemptiveCache<T extends Identifiable>
		extends AbstractCache<T> {
    
    protected AbstractPreemptiveCache(EventBus eventBus, 
            Store<T> store, RemoteReadOnly<T> remote,
            Factory<T, ?> factory ){
        this( eventBus, store, remote, factory, null );
    }
    
    protected AbstractPreemptiveCache(EventBus eventBus,  
            Store<T> store, RemoteReadOnly<T> remote,
            Factory<T, ?> factory,
            CacheManager manager){
        super(eventBus, store, remote, factory, manager);
    }
    
    public T getOrLoadModel(int id){
        T model = getModel(id);
        GWT.log( "TODO AbstractPreemptiveCache decide on impl" );
//        if (model == null){
//            model = remote.newModel();
//        }
        remote.retrieve( id );
        return model;
    }
    
    public List<T> getOrLoadModels(){
        List<T> result = getModels();
        remote.retrieveAll();
        return result;
    }
}