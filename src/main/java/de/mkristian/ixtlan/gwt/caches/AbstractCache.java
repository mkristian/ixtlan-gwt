package de.mkristian.ixtlan.gwt.caches;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEventHandler;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyFactory;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyRemote;


public abstract class AbstractCache<T extends Identifiable>
		implements Cache<T> {

    protected final Store<T> store;

    protected final ReadonlyRemote<T> remote;

	private ReadonlyFactory<T, ?> factory;
    
    protected AbstractCache( EventBus eventBus, 
            Store<T> store,
            ReadonlyRemote<T> remote,
            ReadonlyFactory<T, ?> factory ){
        this( eventBus, store, remote, factory, null );
    }
    
    protected AbstractCache( EventBus eventBus,  
                              Store<T> store, 
                              ReadonlyRemote<T> remote,
                              ReadonlyFactory<T, ?> factory, 
                              CacheManager manager ) {
        this.factory = factory;
        if (manager != null){
            manager.addCache(this);
        }
        this.store = store;
        this.remote = remote;
        eventBus.addHandler( factory.eventType(), new ModelEventHandler<T>() {

            public void onModelEvent(ModelEvent<T> event) {
                dispatchModelEvent(event);
            }
        });
    }
        
    protected String raw( ModelEvent<T> event ){
        return event.getMethod().getResponse().getText();
    }

    protected void dispatchModelEvent(ModelEvent<T> event) {
        switch(event.getAction()){
            case LOAD:
                if (event.getModels() != null){
                    store.replaceAll( event.getModels(), raw( event ) );
                }
                else if (event.getModel() != null){
                    store.update( event.getModel(), raw( event ) );
                }
                break;
            case UPDATE:
            case CREATE:
                store.update( event.getModel(), raw( event ) );
                break;
            case DESTROY:
                store.remove( event.getModel() );
                break;
            case ERROR:
                GWT.log("caught error:", event.getThrowable());
                break;
            default: 
                throw new RuntimeException( "unknown action: " + event.getAction());
        }
    }

    public void purgeAll(){
        store.purgeAll();
    }
    
    public void removeAll(){
        store.removeAll();
    }

    public void remove(T model){
        store.remove(model);
    }

    public T getModel(int id) {
        return store.get(id);
    }
    
    public T getOrLoadModel(int id){
        T model = getModel(id);
        if (model == null){
            remote.retrieve(id);
            model = factory.newModel();
        }
        return model;
    }
    
    public List<T> getModels(){
        return store.getAll();
    }

    public List<T> getOrLoadModels(){
        List<T> result = getModels();
        if( result == null ){
			remote.retrieveAll();
        }
        return result;
    }
}