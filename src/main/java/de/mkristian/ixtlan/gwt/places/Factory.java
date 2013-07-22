package de.mkristian.ixtlan.gwt.places;

import java.util.List;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.event.shared.GwtEvent.Type;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.events.ModelEventHandler;
import de.mkristian.ixtlan.gwt.models.Identifiable;


public interface Factory<R extends Identifiable, 
                           S extends RestfulPlace<R, ?>> {
    
    public S newRestfulPlace( RestfulAction action );
    
    public S newRestfulPlace( int id, RestfulAction action );
    
    public S newRestfulPlace( R model, RestfulAction action );
    
    public S newRestfulPlace( int id, R model, RestfulAction action );
    
    public GenericPlaceTokenizer<R, S> newRestfulPlaceTokenizer();

    public Type<ModelEventHandler<R>> eventType();
    
    public ModelEvent<R> newEvent( Method method, Throwable e );

    public ModelEvent<R> newEvent( Method method, R model, Action action );

    public ModelEvent<R> newEvent( Method method, List<R> models, Action action );

    public R newModel();
    
    public String placeName();
}