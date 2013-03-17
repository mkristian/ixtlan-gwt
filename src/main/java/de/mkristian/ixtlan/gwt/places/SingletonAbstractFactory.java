package de.mkristian.ixtlan.gwt.places;


public abstract class SingletonAbstractFactory<R, S extends RestfulPlace<R, ?>>
        implements SingletonFactory<R, S>{

    @Override
    public S newRestfulPlace(RestfulAction action) {
        return newRestfulPlace( null, action );
    }

    @Override
    public SingletonGenericPlaceTokenizer<R, S> newRestfulPlaceTokenizer() {
        return new SingletonGenericPlaceTokenizer<R, S>( this );
    }
}