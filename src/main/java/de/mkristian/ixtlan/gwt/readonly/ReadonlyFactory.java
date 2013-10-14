package de.mkristian.ixtlan.gwt.readonly;

import java.util.List;

import org.fusesource.restygwt.client.Method;

import de.mkristian.ixtlan.gwt.common.GenericPlaceTokenizer;
import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.singleton.SingletonFactory;

public interface ReadonlyFactory<R extends Identifiable, S extends RestfulPlace<R, ?>>
    extends SingletonFactory<R, S>{
        
    public S newRestfulPlace( int id, RestfulAction action );
    
    public S newRestfulPlace( int id, R model, RestfulAction action );
    
    public GenericPlaceTokenizer<R, S> newRestfulPlaceTokenizer();
    
    public ModelEvent<R> newEvent( Method method, List<R> models, Action action );
}