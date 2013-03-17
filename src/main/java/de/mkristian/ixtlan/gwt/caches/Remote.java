package de.mkristian.ixtlan.gwt.caches;

import org.fusesource.restygwt.client.Method;

import de.mkristian.ixtlan.gwt.models.Identifiable;

public interface Remote<T extends Identifiable> extends RemoteReadOnly<T> {

    void fireCreate(Method method, T model);

    void fireUpdate(Method method, T model);

    void fireDelete(Method method, T model);

    T newModel();
    
    void create(T model);
    
    void update(T model);
    
    void delete(T model);
}