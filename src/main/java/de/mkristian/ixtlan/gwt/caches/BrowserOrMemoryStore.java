package de.mkristian.ixtlan.gwt.caches;

import java.util.List;

import org.fusesource.restygwt.client.JsonEncoderDecoder;

import com.google.gwt.storage.client.Storage;

import de.mkristian.ixtlan.gwt.models.Identifiable;

public class BrowserOrMemoryStore<T extends Identifiable> implements Store<T> {

    private final Store<T> store;
    private boolean storeCollections;

    public BrowserOrMemoryStore(JsonEncoderDecoder<T> coder, String key){
        this( coder, key, true );
    }
    
    public BrowserOrMemoryStore(JsonEncoderDecoder<T> coder, String key, boolean storeCollections ){
        this.storeCollections = storeCollections;
        if ( Storage.isLocalStorageSupported() ){
            store = new BrowserStore<T>(coder, key);
        }
        else {
            store = new MemoryStore<T>();
        }
    }
    
    public void update(T model, String json) {
        store.update(model, json);
    }

    public void replaceAll(List<T> models, String json) {
        if ( storeCollections ) {
            store.replaceAll(models, json);
        }
    }

    public T get(int id) {
        return store.get(id);
    }

    public List<T> getAll() {
        if (storeCollections) {
            return store.getAll();
        }
        else {
            return null;
        }
    }

    public void remove(T model) {
        store.remove(model);
    }

    public void removeAll() {
        store.removeAll();
    }

    public void purgeAll() {
        store.purgeAll();
    }
}
