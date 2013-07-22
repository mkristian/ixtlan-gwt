package de.mkristian.ixtlan.gwt.views;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;


public interface SingletonView<T> extends DetailView {

    void show();

    void edit();

    void reset( T model );

    boolean isDirty();

    T flush();

	HasTapHandlers getCancelButton();

	HasTapHandlers getReloadButton();

	HasTapHandlers getEditButton();

	HasTapHandlers getSaveButton();
}