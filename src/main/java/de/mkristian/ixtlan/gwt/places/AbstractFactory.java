package de.mkristian.ixtlan.gwt.places;

import de.mkristian.ixtlan.gwt.models.Identifiable;

public abstract class AbstractFactory<R extends Identifiable, 
                                         S extends RestfulPlace<R, ?>>
        implements Factory<R, S>{

    @Override
    public S newRestfulPlace( RestfulAction action ) {
        return newRestfulPlace( 0, null, action );
    }

    @Override
    public S newRestfulPlace( int id, RestfulAction action ) {
        return newRestfulPlace( id, null, action );
    }

    @Override
    public S newRestfulPlace( R model, RestfulAction action ) {
    	if ( model == null ) {
    		return newRestfulPlace( 0, model, action );
    	}
    	else {
    		return newRestfulPlace( model.getId(), model, action );
    	}
    }

    @Override
    public GenericPlaceTokenizer<R, S> newRestfulPlaceTokenizer() {
        return new GenericPlaceTokenizer<R, S>( this );
    }
}