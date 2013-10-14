package de.mkristian.ixtlan.gwt.singleton;

import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;

public abstract class AbstractSingletonFactory<R, S extends RestfulPlace<R, ?>>
        implements SingletonFactory<R, S>{

    @Override
    public S newRestfulPlace( RestfulAction action ) {
        return newRestfulPlace( null, action );
    }


    @Override
    public S newRestfulPlace( R model, RestfulAction action ) {
        return newRestfulPlace( model, action );
    }

    @Override
    public SingletonGenericPlaceTokenizer<R, S> newRestfulPlaceTokenizer() {
        return new SingletonGenericPlaceTokenizer<R, S>( this );
    }
}