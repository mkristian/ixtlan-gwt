package de.mkristian.ixtlan.gwt.client.audits;



import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.gwt.readonly.ReadonlyListView;

@ImplementedBy( AuditListViewImpl.class )
public interface AuditListView extends ReadonlyListView<Audit> {
}