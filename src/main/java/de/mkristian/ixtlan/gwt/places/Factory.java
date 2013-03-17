package de.mkristian.ixtlan.gwt.places;

import de.mkristian.ixtlan.gwt.models.Identifiable;


public interface Factory<R extends Identifiable, 
                           S extends RestfulPlace<R, ?>> {
    
    public S newRestfulPlace( RestfulAction action );
    
    public S newRestfulPlace( int id, RestfulAction action );
    
    public S newRestfulPlace( R model, RestfulAction action );
    
    public S newRestfulPlace( int id, R model, RestfulAction action );
    
    public GenericPlaceTokenizer<R, S> newRestfulPlaceTokenizer();
}