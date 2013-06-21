package de.mkristian.ixtlan.gwt.views;

import de.mkristian.ixtlan.gwt.presenters.SingletonPresenter;


public interface SingletonView<T> extends DetailView {

    void setPresenter( SingletonPresenter<T> presenter );

    void show( T model );

    void edit( T model );

    boolean isDirty();
}