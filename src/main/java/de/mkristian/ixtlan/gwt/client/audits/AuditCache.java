package de.mkristian.ixtlan.gwt.client.audits;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.fusesource.restygwt.client.JsonEncoderDecoder;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.caches.PreemptiveCache;
import de.mkristian.ixtlan.gwt.caches.MemoryStore;
import de.mkristian.ixtlan.gwt.caches.Store;

@Singleton
public class AuditCache extends PreemptiveCache<Audit>{

	static interface AuditCoder extends JsonEncoderDecoder<Audit>{
    }
    static AuditCoder coder = GWT.create( AuditCoder.class );

    private static Store<Audit> store(){
        return new MemoryStore<Audit>( );
    }
    
    @SuppressWarnings("unchecked")
	@Inject
    AuditCache( EventBus eventBus, AuditReadonlyRemote remote, 
    		@SuppressWarnings("rawtypes") AuditFactory factory ) {
        super( eventBus, store(), remote, factory );
    }
}