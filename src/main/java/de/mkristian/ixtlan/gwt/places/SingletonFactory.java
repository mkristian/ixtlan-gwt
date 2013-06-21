package de.mkristian.ixtlan.gwt.places;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.event.shared.GwtEvent.Type;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.events.ModelEventHandler;


public interface SingletonFactory<R, S extends RestfulPlace<R, ?>> {
    
    public S newRestfulPlace( RestfulAction action );
    
    public S newRestfulPlace( R model, RestfulAction action );
    
    public SingletonGenericPlaceTokenizer<R, S> newRestfulPlaceTokenizer();

    public Type<ModelEventHandler<R>> eventType();
    
    public ModelEvent<R> newEvent( Method method, Throwable e );
    
    public ModelEvent<R> newEvent( Method method, R model, Action action );

    public String placeName();
}