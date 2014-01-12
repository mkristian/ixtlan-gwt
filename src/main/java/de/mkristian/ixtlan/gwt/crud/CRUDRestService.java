package de.mkristian.ixtlan.gwt.crud;

import java.util.List;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

public interface CRUDRestService<T> extends RestService {

    void index( MethodCallback<List<T>> callback );

    void show( int id, MethodCallback<T> callback );

    void create( T value, MethodCallback<T> callback );

    void update( T value, MethodCallback<T> callback );

    void destroy( T value, MethodCallback<Void> callback );
}
