package de.mkristian.ixtlan.gwt.client.audits;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.mkristian.ixtlan.gwt.readonly.ReadonlyPresenterImpl;
import de.mkristian.ixtlan.gwt.utils.ErrorHandlerWithDisplay;

@Singleton
public class AuditPresenter extends ReadonlyPresenterImpl<Audit> {

	@Inject
	public AuditPresenter( ErrorHandlerWithDisplay errors,
			AuditView view,
			AuditListView listView,
			AuditCache cache,
			AuditReadonlyRemote remote ) {
		super( errors, view, listView, cache, remote );
	}
	
}