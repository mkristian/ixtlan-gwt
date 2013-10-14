package de.mkristian.ixtlan.gwt.client.audits;



import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.gwt.readonly.ReadonlyView;

@ImplementedBy( AuditViewImpl.class )
public interface AuditView extends ReadonlyView<Audit> {
}
