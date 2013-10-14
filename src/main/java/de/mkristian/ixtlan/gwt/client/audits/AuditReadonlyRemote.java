package de.mkristian.ixtlan.gwt.client.audits;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.readonly.ReadonlyRemoteAdapter;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;


@Singleton
public class AuditReadonlyRemote extends ReadonlyRemoteAdapter<Audit> {

    private final AuditsRestService restService;
    
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Inject
    protected AuditReadonlyRemote( NetworkIndicator networkIndicator, 
                                   EventBus eventBus, 
                                   AuditsRestService restService,
                                   AuditFactory factory  ) {
        super( eventBus, networkIndicator, factory );
        this.restService = restService;
    }

    @Override
    public void retrieveAll() {
        networkIndicator.loading();
        restService.index(newRetrieveAllCallback());
    }

    @Override
    public void retrieve(int id) {
        networkIndicator.loading();
        restService.show(id, newRetrieveCallback());
    }
}