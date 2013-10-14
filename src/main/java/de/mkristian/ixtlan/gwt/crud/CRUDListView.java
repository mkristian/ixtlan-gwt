package de.mkristian.ixtlan.gwt.crud;

import java.util.List;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyListView;


public interface CRUDListView<T extends Identifiable> 
		extends ReadonlyListView<T> {

    void reset( List<T> models, RestfulActionEnum permission );

    void remove( T model );
}