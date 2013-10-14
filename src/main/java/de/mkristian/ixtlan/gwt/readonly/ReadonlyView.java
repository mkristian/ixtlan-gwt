package de.mkristian.ixtlan.gwt.readonly;

import de.mkristian.ixtlan.gwt.common.DetailView;


// use T instead of T extends Identifiable to reuse with the SingletonView
public interface ReadonlyView<T> extends DetailView {
    
    void reset(T model);
}