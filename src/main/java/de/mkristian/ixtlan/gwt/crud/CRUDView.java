package de.mkristian.ixtlan.gwt.crud;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.singleton.SingletonView;

public interface CRUDView<T extends Identifiable>
            extends SingletonView<T> {

    void create( RestfulActionEnum permission );
	
	HasTapHandlers getCreateButton();
	
	HasTapHandlers getDeleteButton();
}