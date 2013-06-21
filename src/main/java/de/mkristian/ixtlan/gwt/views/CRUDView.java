package de.mkristian.ixtlan.gwt.views;

import com.google.gwt.user.client.ui.IsWidget;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.presenters.CRUDPresenter;

public interface CRUDView<T extends Identifiable,
                            S extends CRUDPresenter<T>>
            extends IsWidget {

    void setPresenter(S presenter);

    void show(T model);

    void edit(T model);

    void reset(T model);

    void showNew();

    boolean isDirty();
}