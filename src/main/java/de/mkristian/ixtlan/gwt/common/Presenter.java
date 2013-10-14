package de.mkristian.ixtlan.gwt.common;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.places.RestfulAction;

public interface Presenter<T> {

    void setDisplay(AcceptsOneWidget display);

    void unknownAction(RestfulAction action);

    void unknownAction(Action action);

    void onError(Method method, Throwable e);
    
    void reload();
    
    void reset( T model );
    
    void show( T model );

//    void edit( T model );

    T current();

}