package de.mkristian.ixtlan.gwt.errors;



import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.gwt.views.ReadOnlyListView;

@ImplementedBy( ErrorListViewImpl.class )
public interface ErrorListView extends ReadOnlyListView<Error> {
}