package de.mkristian.ixtlan.gwt.client.errors;

import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyFactory;

public interface ErrorFactory<T extends RestfulPlace<Error, ?>> 
	extends ReadonlyFactory<Error, T>{
}