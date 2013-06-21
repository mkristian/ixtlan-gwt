package de.mkristian.ixtlan.gwt.audits;



import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.gwt.views.ReadOnlyView;

@ImplementedBy( AuditViewImpl.class )
public interface AuditView extends ReadOnlyView<Audit> {
}
