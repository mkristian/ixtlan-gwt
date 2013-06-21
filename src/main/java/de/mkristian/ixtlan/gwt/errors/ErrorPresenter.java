package de.mkristian.ixtlan.gwt.errors;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.mkristian.ixtlan.gwt.presenters.ReadOnlyPresenterImpl;
import de.mkristian.ixtlan.gwt.utils.ErrorHandlerWithDisplay;

@Singleton
public class ErrorPresenter extends ReadOnlyPresenterImpl<Error> {

	@Inject
	public ErrorPresenter(ErrorHandlerWithDisplay errors,
			ErrorView view, ErrorListView listView,
			ErrorRemoteReadOnly remote) {
		super(errors, view, listView, remote);
	}
	
}