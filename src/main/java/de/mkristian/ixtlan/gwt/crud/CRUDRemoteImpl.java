package de.mkristian.ixtlan.gwt.crud;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;

@Singleton
public class CRUDRemoteImpl<T extends Identifiable>
            extends AbstractCRUDRemote<T> {

    private final CRUDRestService<T> restService;

    @Inject
    public CRUDRemoteImpl( NetworkIndicator networkIndicator,
                              EventBus eventBus,
                              CRUDRestService<T> restService,
                              AbstractCRUDFactory<T, ?> factory) {
        super( eventBus, networkIndicator, factory );
        this.restService = restService;
    }

    @Override
    public void retrieve( int id ) {
        super.retrieve( id );
        restService.show( id, newRetrieveCallback() );
    }

    @Override
    public void retrieveAll() {
        super.retrieveAll();
        restService.index( newRetrieveAllCallback() );
    }

    @Override
    public void create( T model ) {
        super.create( model );
        restService.create( model, newCreateCallback() );
    }

    @Override
    public void update( T model ) {
        super.update( model );
        restService.update( model, newUpdateCallback() );
    }

    @Override
    public void delete( T model ) {
        super.delete( model );
        restService.destroy( model, newDeleteCallback( model ) );
    }
}
