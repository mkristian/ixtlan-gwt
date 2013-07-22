package de.mkristian.ixtlan.gwt.presenters;

import java.util.List;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.views.CRUDListView;
import de.mkristian.ixtlan.gwt.views.CRUDView;



public interface CRUDPresenter<T extends Identifiable> extends Presenter {

    CRUDView<T> view();

    CRUDListView<T> listView();
    
    void reload();
    
    void create( T model );

    void save( T model );

    void delete( T model );

    void reset( T model );
    void reset( List<T> models );

    void showNew();
    void showAll();
    void show( int id );
    void show( T model );

    void edit( int id );
    void edit( T model );
    
    boolean isDirty();

    T current();
}