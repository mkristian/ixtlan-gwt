package de.mkristian.ixtlan.gwt.crud;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyPresenter;



public interface CRUDPresenter<T extends Identifiable>
        extends ReadonlyPresenter<T> {

    CRUDView<T> view();

    CRUDListView<T> listView();
    
  //  void reload();
    
    void create( T model );

    void update( T model );

    void delete( T model );

  //  void reset( T model );
    //void reset( List<T> models );

    //void showNew();
    void showNew( T model );
    
//    void showAll();
    
  //  void show( int id );
    //void show( T model );

    void edit( int id );
    void edit( T model );
    
    boolean isDirty();

 //   T current();
}