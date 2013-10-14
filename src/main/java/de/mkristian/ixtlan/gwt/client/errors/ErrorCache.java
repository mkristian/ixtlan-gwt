package de.mkristian.ixtlan.gwt.client.errors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.fusesource.restygwt.client.JsonEncoderDecoder;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.caches.AbstractPreemptiveCache;
import de.mkristian.ixtlan.gwt.caches.MemoryStore;
import de.mkristian.ixtlan.gwt.caches.Store;

@Singleton
public class ErrorCache extends AbstractPreemptiveCache<Error>{

    static interface ErrorCoder extends JsonEncoderDecoder<Error>{
    }
    static ErrorCache.ErrorCoder coder = GWT.create( ErrorCache.ErrorCoder.class );

    private static Store<Error> store(){
        return new MemoryStore<Error>( );
    }
    
    @SuppressWarnings("unchecked")
	@Inject
    ErrorCache( EventBus eventBus, ErrorRemoteReadOnly remote, 
    		@SuppressWarnings("rawtypes") ErrorFactory factory ) {
        super( eventBus, store(), remote, factory );
    }
}