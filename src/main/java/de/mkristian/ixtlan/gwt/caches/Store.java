package de.mkristian.ixtlan.gwt.caches;

import java.util.List;

import de.mkristian.ixtlan.gwt.models.Identifiable;

public interface Store<T extends Identifiable> {

    void update(T model, String json);

    void replaceAll(List<T> models, String json);

    T get(int id);

    List<T> getAll();

    void remove(T model);

    void removeAll();
    
    void purgeAll();

}