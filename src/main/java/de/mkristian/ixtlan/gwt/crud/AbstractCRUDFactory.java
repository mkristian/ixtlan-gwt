package de.mkristian.ixtlan.gwt.crud;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.readonly.AbstractReadonlyFactory;

public abstract class AbstractCRUDFactory<R extends Identifiable, 
                                         S extends RestfulPlace<R, ?>>
        extends AbstractReadonlyFactory<R, S>
        implements CRUDFactory<R, S>{
}