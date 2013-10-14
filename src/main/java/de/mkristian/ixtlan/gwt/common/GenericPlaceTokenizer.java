package de.mkristian.ixtlan.gwt.common;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyFactory;
import de.mkristian.ixtlan.gwt.singleton.SingletonGenericPlaceTokenizer;


public class GenericPlaceTokenizer<R extends Identifiable, S extends RestfulPlace<R, ?>> 
        extends SingletonGenericPlaceTokenizer<R, S> {
    
    public GenericPlaceTokenizer( ReadonlyFactory<R, S> factory ){
        super( factory );
    }
    
    @Override
    protected S newRestfulPlace( RestfulAction action ) {
        return factory.newRestfulPlace( action );
    }

    @Override
    protected S newRestfulPlace( int id, RestfulAction action ) {
        return (( ReadonlyFactory<R, S>) factory).newRestfulPlace( id, action );
    }
}
