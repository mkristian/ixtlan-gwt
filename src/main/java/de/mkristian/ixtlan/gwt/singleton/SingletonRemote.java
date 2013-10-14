package de.mkristian.ixtlan.gwt.singleton;

import org.fusesource.restygwt.client.Method;

import de.mkristian.ixtlan.gwt.common.Remote;

public interface SingletonRemote<T>
        extends Remote<T>{

    void fireUpdate(Method method, T model);
    
    void retrieve();
    
    void update(T model);
}