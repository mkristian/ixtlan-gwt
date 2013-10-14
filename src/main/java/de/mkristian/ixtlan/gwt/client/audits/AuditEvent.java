package de.mkristian.ixtlan.gwt.client.audits;

import java.util.List;

import org.fusesource.restygwt.client.Method;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEventHandler;

public class AuditEvent extends ModelEvent<Audit> {

    public static final Type<ModelEventHandler<Audit>> TYPE = new Type<ModelEventHandler<Audit>>();
    
    public AuditEvent( Method method, Audit model, 
                ModelEvent.Action action) {
        super( method, model, action );
    }

    public AuditEvent( Method method, List<Audit> models,
                ModelEvent.Action action) {
        super( method, models, action );
    }

    public AuditEvent( Method method, Throwable e ) {
        super( method, e );
    }

    @Override
    public Type<ModelEventHandler<Audit>> getAssociatedType() {
        return TYPE;
    }
}