package de.mkristian.ixtlan.gwt.errors;

import de.mkristian.ixtlan.gwt.places.Factory;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;

public interface ErrorFactory<T extends RestfulPlace<Error, ?>> 
	extends Factory<Error, T>{
	
}