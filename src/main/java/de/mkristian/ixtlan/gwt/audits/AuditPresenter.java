package de.mkristian.ixtlan.gwt.audits;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.mkristian.ixtlan.gwt.presenters.ReadOnlyPresenterImpl;
import de.mkristian.ixtlan.gwt.utils.ErrorHandlerWithDisplay;

@Singleton
public class AuditPresenter extends ReadOnlyPresenterImpl<Audit> {

	@Inject
	public AuditPresenter(ErrorHandlerWithDisplay errors,
			AuditView view, AuditListView listView,
			AuditRemoteReadOnly remote) {
		super(errors, view, listView, remote);
	}
	
}