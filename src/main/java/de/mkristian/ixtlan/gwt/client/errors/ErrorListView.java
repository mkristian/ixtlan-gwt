package de.mkristian.ixtlan.gwt.client.errors;



import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.gwt.readonly.ReadonlyListView;

@ImplementedBy( ErrorListViewImpl.class )
public interface ErrorListView extends ReadonlyListView<Error> {
}