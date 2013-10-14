package de.mkristian.ixtlan.gwt.crud;

import org.fusesource.restygwt.client.Method;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyRemote;
import de.mkristian.ixtlan.gwt.singleton.SingletonRemote;

public interface CRUDRemote<T extends Identifiable> 
        extends ReadonlyRemote<T>, SingletonRemote<T> {

    void fireCreate(Method method, T model);

    void fireDelete(Method method, T model);
    
    void create(T model);
    
    void delete(T model);
}