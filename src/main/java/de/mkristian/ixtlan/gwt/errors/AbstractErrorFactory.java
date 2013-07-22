package de.mkristian.ixtlan.gwt.errors;

import java.util.List;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.event.shared.GwtEvent.Type;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.events.ModelEventHandler;
import de.mkristian.ixtlan.gwt.places.AbstractFactory;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;

public abstract class AbstractErrorFactory <T extends RestfulPlace<Error, ?>>
		extends AbstractFactory<Error, T> 
		implements ErrorFactory<T> {

	@Override
	public Type<ModelEventHandler<Error>> eventType() {
	    return ErrorEvent.TYPE;
	}

	@Override
	public ModelEvent<Error> newEvent(Method method, Throwable e) {
	    return new ErrorEvent( method, e );
	}

	@Override
	public ModelEvent<Error> newEvent(Method method, Error model, Action action) {
	    return new ErrorEvent( method, model, action );
	}

	@Override
	public ModelEvent<Error> newEvent(Method method, List<Error> models, Action action) {
		return new ErrorEvent( method, models, action );
	}

	@Override
	public Error newModel() {
		return new Error();
	}
}