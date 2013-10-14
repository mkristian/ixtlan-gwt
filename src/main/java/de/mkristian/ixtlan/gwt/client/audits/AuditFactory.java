package de.mkristian.ixtlan.gwt.client.audits;

import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyFactory;

public interface AuditFactory<T extends RestfulPlace<Audit, ?>>
    extends ReadonlyFactory<Audit, T>{
	
}