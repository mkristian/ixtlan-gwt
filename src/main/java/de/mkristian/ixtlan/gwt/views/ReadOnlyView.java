package de.mkristian.ixtlan.gwt.views;


public interface ReadOnlyView<T> extends DetailView {
    
    void show(T model);
}