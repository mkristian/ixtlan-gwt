package de.mkristian.ixtlan.gwt.presenters;

import de.mkristian.ixtlan.gwt.views.SingletonView;

public interface SingletonPresenter<T>
            extends Presenter {

    SingletonView<T> view();
    
    void reload();

    void save( T model );

    void reset( T model );
    
    void show();
    void show( T model );

    void edit();
    void edit( T model );

    boolean isDirty();

    T get();

}