package de.mkristian.ixtlan.gwt.singleton;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyView;


public interface SingletonView<T> extends ReadonlyView<T> {

    void show( RestfulActionEnum permission );

    void edit( RestfulActionEnum permission );

    boolean isDirty();

    T flush();

	HasTapHandlers getCancelButton();

	HasTapHandlers getReloadButton();

	HasTapHandlers getEditButton();

	HasTapHandlers getSaveButton();
}