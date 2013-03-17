package de.mkristian.ixtlan.gwt.audits;

import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;


public abstract class AuditPlace<S> extends RestfulPlace<Audit, S> {
    
    public static final String NAME = "audits";
    
    public AuditPlace( int id, Audit model, RestfulAction restfulAction ) {
        super( id, model, restfulAction, NAME );
    }
}