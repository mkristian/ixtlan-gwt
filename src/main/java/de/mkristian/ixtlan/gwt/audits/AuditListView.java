package de.mkristian.ixtlan.gwt.audits;



import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.gwt.views.ReadOnlyListView;

@ImplementedBy( AuditListViewImpl.class )
public interface AuditListView extends ReadOnlyListView<Audit> {
}