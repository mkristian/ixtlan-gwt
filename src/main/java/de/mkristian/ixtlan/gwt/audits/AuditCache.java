package de.mkristian.ixtlan.gwt.audits;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.fusesource.restygwt.client.JsonEncoderDecoder;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.caches.AbstractPreemptiveCache;
import de.mkristian.ixtlan.gwt.caches.MemoryStore;
import de.mkristian.ixtlan.gwt.caches.Store;

@Singleton
public class AuditCache extends AbstractPreemptiveCache<Audit>{

	static interface AuditCoder extends JsonEncoderDecoder<Audit>{
    }
    static AuditCoder coder = GWT.create( AuditCoder.class );

    private static Store<Audit> store(){
        return new MemoryStore<Audit>( );
    }
    
    @SuppressWarnings("unchecked")
	@Inject
    AuditCache( EventBus eventBus, AuditRemoteReadOnly remote, 
    		@SuppressWarnings("rawtypes") AuditFactory factory ) {
        super( eventBus, store(), remote, factory );
    }
}