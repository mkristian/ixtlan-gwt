package de.mkristian.ixtlan.gwt.readonly;

import de.mkristian.ixtlan.gwt.common.GenericPlaceTokenizer;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;

public abstract class AbstractReadonlyFactory<R extends Identifiable, 
                                         S extends RestfulPlace<R, ?>>
        implements ReadonlyFactory<R, S>{

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