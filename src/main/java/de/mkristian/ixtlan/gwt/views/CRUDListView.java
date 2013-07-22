package de.mkristian.ixtlan.gwt.views;

import java.util.List;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;


public interface CRUDListView<T extends Identifiable> 
		extends DetailListView {

    void reset( List<T> models, RestfulActionEnum permission );

    void remove( T model );
}