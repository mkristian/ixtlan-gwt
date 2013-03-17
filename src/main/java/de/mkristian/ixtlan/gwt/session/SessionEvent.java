package de.mkristian.ixtlan.gwt.session;

import java.util.List;

import org.fusesource.restygwt.client.Method;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEventHandler;


public class SessionEvent extends ModelEvent<Session> {

    public static final Type<ModelEventHandler<Session>> TYPE = new Type<ModelEventHandler<Session>>();
    
    public SessionEvent( Method method, Session model, 
                ModelEvent.Action action) {
        super( method, model, action );
    }

    public SessionEvent( Method method, List<Session> models,
                ModelEvent.Action action) {
        super( method, models, action );
    }

    public SessionEvent( Method method, Throwable e ) {
        super( method, e );
    }

    @Override
    public Type<ModelEventHandler<Session>> getAssociatedType() {
        return TYPE;
    }
}