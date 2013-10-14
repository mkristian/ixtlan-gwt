package de.mkristian.ixtlan.gwt.singleton;

import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;


public class SingletonGenericPlaceTokenizer<R, S extends RestfulPlace<R, ?>> 
        extends SingletonRestfulPlaceTokenizer<S> {
    
    protected final SingletonFactory<R, S> factory;
    
    public SingletonGenericPlaceTokenizer(SingletonFactory<R, S> factory){
        this.factory = factory;
    }
    
    @Override
    protected S newRestfulPlace( RestfulAction action ) {
        return factory.newRestfulPlace( action );
    }

    @Override
    protected S newRestfulPlace( int id, RestfulAction action ){
        return null;
    }
}
