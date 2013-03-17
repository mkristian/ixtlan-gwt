package de.mkristian.ixtlan.gwt.caches;

import java.util.List;

import de.mkristian.ixtlan.gwt.models.Identifiable;


public interface Cache<T extends Identifiable> {

    void purgeAll();
    
    void remove(T model);

    void removeAll();
    
    T getModel(int id);
    
    T getOrLoadModel(int id);
    
    List<T> getModels();

    List<T> getOrLoadModels();
}