package de.mkristian.ixtlan.gwt.views;

import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;

public class ModelButton<T> extends GeneralModelButton<T, RestfulActionEnum> {
    public ModelButton(RestfulActionEnum action, T model){
        super(action, model);
    }
}