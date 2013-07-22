package de.mkristian.ixtlan.gwt.audits;

import de.mkristian.ixtlan.gwt.places.Factory;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;

public interface AuditFactory<T extends RestfulPlace<Audit, ?>> extends Factory<Audit, T>{
	
}