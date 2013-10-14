package de.mkristian.ixtlan.gwt.readonly;

import java.util.List;

import de.mkristian.ixtlan.gwt.common.Presenter;
import de.mkristian.ixtlan.gwt.models.Identifiable;

public interface ReadonlyPresenter<T extends Identifiable> 
            extends Presenter<T> {

    ReadonlyView<T> view();

    ReadonlyListView<T> listView();

//    void reload();

//    void reset( T model );
    void reset( List<T> models );

    void showAll();

    void show( int id );
//    void show( T model );

  //  T current();
}