package de.mkristian.ixtlan.gwt.caches;

import java.util.List;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyFactory;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyRemote;

public class PreemptiveCache<T extends Identifiable>
		extends AbstractCache<T> {
    
    public PreemptiveCache(EventBus eventBus, 
            Store<T> store, ReadonlyRemote<T> remote,
            ReadonlyFactory<T, ?> factory ){
        this( eventBus, store, remote, factory, null );
    }
    
    public PreemptiveCache(EventBus eventBus,  
            Store<T> store, ReadonlyRemote<T> remote,
            ReadonlyFactory<T, ?> factory,
            CacheManager manager){
        super(eventBus, store, remote, factory, manager);
    }
    
    public T getOrLoadModel(int id){
        T model = getModel(id);
        if (model == null){
            model = factory.newModel();
        }
        remote.retrieve( id );
        return model;
    }
    
    public List<T> getOrLoadModels(){
        List<T> result = getModels();
        remote.retrieveAll();
        return result;
    }
}