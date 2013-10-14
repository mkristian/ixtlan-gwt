package de.mkristian.ixtlan.gwt.crud;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyFactory;
import de.mkristian.ixtlan.gwt.singleton.SingletonFactory;

public interface CRUDFactory<R extends Identifiable, S extends RestfulPlace<R, ?>>
        extends SingletonFactory<R, S>, ReadonlyFactory<R, S> {

}