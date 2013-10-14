package de.mkristian.ixtlan.gwt.client.audits;


import com.google.gwt.place.shared.PlaceController;

import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.readonly.AbstractReadonlyActivity;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyFactory;
import de.mkristian.ixtlan.gwt.session.SessionFacade;

public class AbstractAuditActivity extends AbstractReadonlyActivity<Audit> {
	
    public AbstractAuditActivity( RestfulPlace<Audit, ?> place,
                AuditPresenter presenter,
                PlaceController places,
                ReadonlyFactory<Audit, ?> factory,
                SessionFacade session, 
                String eventId ) {
        super( place, presenter, places, factory, session, 
        		eventId );
        presenter.view().getHeaderText().setText( "Audit" );
        presenter.listView().getHeaderText().setText( "Audit List" );
    }
}
