package de.mkristian.ixtlan.gwt.errors;



import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.gwt.views.ReadOnlyView;

@ImplementedBy( ErrorViewImpl.class )
public interface ErrorView extends ReadOnlyView<Error> {
}
