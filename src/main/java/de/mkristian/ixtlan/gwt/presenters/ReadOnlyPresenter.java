package de.mkristian.ixtlan.gwt.presenters;

import java.util.List;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.views.ReadOnlyListView;
import de.mkristian.ixtlan.gwt.views.ReadOnlyView;

public interface ReadOnlyPresenter<T extends Identifiable> 
            extends Presenter {

    ReadOnlyView<T> view();

    ReadOnlyListView<T> listView();
    
    void reset( T model );

    void reset( List<T> models );

    void showAll();

    void show( int id );

	void reload();

}