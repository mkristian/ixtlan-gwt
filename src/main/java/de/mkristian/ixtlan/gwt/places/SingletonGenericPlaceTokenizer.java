package de.mkristian.ixtlan.gwt.places;


public class SingletonGenericPlaceTokenizer<R, S extends RestfulPlace<R, ?>> 
        extends SingletonRestfulPlaceTokenizer<S> {
    
    private final SingletonFactory<R, S> factory;
    
    public SingletonGenericPlaceTokenizer(SingletonFactory<R, S> factory){
        this.factory = factory;
    }
    
    @Override
    protected S newRestfulPlace( RestfulAction action ) {
        return factory.newRestfulPlace( action );
    }
}
