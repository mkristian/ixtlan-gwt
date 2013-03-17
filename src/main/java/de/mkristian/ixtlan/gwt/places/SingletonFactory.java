package de.mkristian.ixtlan.gwt.places;


public interface SingletonFactory<R, S extends RestfulPlace<R, ?>> {
    
    public S newRestfulPlace( RestfulAction action );
    
    public S newRestfulPlace( R model, RestfulAction action );
    
    public SingletonGenericPlaceTokenizer<R, S> newRestfulPlaceTokenizer();
}