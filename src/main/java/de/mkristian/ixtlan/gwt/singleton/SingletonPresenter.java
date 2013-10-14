package de.mkristian.ixtlan.gwt.singleton;

import de.mkristian.ixtlan.gwt.common.Presenter;

public interface SingletonPresenter<T>
            extends Presenter<T> {

    SingletonView<T> view();
    
   // void reload();

    void update( T model );

 //   void reset( T model );
    
  //  void show();
   // void show( T model );

    //void edit();
    void edit( T model );

    boolean isDirty();

   // T current();
}