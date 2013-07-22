package de.mkristian.ixtlan.gwt.views;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;

public interface CRUDView<T extends Identifiable>
            extends DetailView {

    void create(RestfulActionEnum permission);

    void show(RestfulActionEnum permission);

    void edit(RestfulActionEnum permission);

    void reset( T model );

    boolean isDirty();

    T flush();
	
	HasTapHandlers getCreateButton();
	
	HasTapHandlers getCancelButton();

	HasTapHandlers getEditButton();

	HasTapHandlers getSaveButton();

	HasTapHandlers getDeleteButton();
}