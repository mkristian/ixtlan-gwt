package de.mkristian.ixtlan.gwt.places;

import de.mkristian.ixtlan.gwt.models.Identifiable;


public class GenericPlaceTokenizer<R extends Identifiable, S extends RestfulPlace<R, ?>> 
        extends RestfulPlaceTokenizer<S> {
    
    private final Factory<R, S> factory;
    
    public GenericPlaceTokenizer(Factory<R, S> factory){
        this.factory = factory;
    }
    
    @Override
    protected S newRestfulPlace( RestfulAction action ) {
        return factory.newRestfulPlace( action );
    }

    @Override
    protected S newRestfulPlace( int id, RestfulAction action ) {
        return factory.newRestfulPlace( id, action );
    }
}
