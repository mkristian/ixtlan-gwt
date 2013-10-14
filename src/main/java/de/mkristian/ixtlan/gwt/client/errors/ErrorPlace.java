package de.mkristian.ixtlan.gwt.client.errors;

import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;

public abstract class ErrorPlace<S> extends RestfulPlace<Error, S> {
    
    public static final String NAME = "errors";
    
    public ErrorPlace(int id, Error model, RestfulAction restfulAction) {
        super(id, model, restfulAction, NAME);
    }
}