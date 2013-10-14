package de.mkristian.ixtlan.gwt.client.errors;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.mkristian.ixtlan.gwt.readonly.ReadonlyPresenterImpl;
import de.mkristian.ixtlan.gwt.utils.ErrorHandlerWithDisplay;

@Singleton
public class ErrorPresenter extends ReadonlyPresenterImpl<Error> {

	@Inject
	public ErrorPresenter( ErrorHandlerWithDisplay errors,
			ErrorView view, 
			ErrorListView listView,
			ErrorCache cache,
			ErrorRemoteReadOnly remote ) {
		super( errors, view, listView, cache, remote );
	}
	
}