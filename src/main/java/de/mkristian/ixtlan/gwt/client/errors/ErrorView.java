package de.mkristian.ixtlan.gwt.client.errors;



import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.gwt.readonly.ReadonlyView;

@ImplementedBy( ErrorViewImpl.class )
public interface ErrorView extends ReadonlyView<Error> {
}
