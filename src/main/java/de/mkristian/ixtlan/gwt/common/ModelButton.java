package de.mkristian.ixtlan.gwt.common;

import de.mkristian.ixtlan.gwt.places.RestfulAction;

public class ModelButton<T> extends GeneralModelButton<T, RestfulAction> {
    public ModelButton(RestfulAction action, T model){
        super(action, model);
    }
}