package de.mkristian.ixtlan.gwt.audits;

import java.util.List;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.event.shared.GwtEvent.Type;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.events.ModelEventHandler;
import de.mkristian.ixtlan.gwt.places.AbstractFactory;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;

public abstract class AbstractAuditFactory<T extends RestfulPlace<Audit, ?>>
		extends AbstractFactory<Audit, T>
		implements AuditFactory<T> {

    @Override
    public Type<ModelEventHandler<Audit>> eventType() {
        return AuditEvent.TYPE;
    }

    @Override
    public ModelEvent<Audit> newEvent(Method method, Throwable e) {
        return new AuditEvent( method, e );
    }

    @Override
    public ModelEvent<Audit> newEvent(Method method, Audit model, Action action) {
        return new AuditEvent( method, model, action );
    }

	@Override
	public ModelEvent<Audit> newEvent(Method method, List<Audit> models,
			Action action) {
		return new AuditEvent( method, models, action );
	}
	
	@Override
	public Audit newModel() {
		return new Audit();
	}
}