package de.mkristian.ixtlan.gwt.audits;


import com.google.gwt.place.shared.PlaceController;

import de.mkristian.ixtlan.gwt.activities.AbstractReadOnlyActivity;
import de.mkristian.ixtlan.gwt.places.Factory;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.session.SessionFacade;

public class AbstractAuditActivity extends AbstractReadOnlyActivity<Audit> {
	
    public AbstractAuditActivity( RestfulPlace<Audit, ?> place,
                AuditPresenter presenter,
                PlaceController places,
                Factory<Audit, ?> factory,
                SessionFacade session, 
                String eventId ) {
        super( place, presenter, places, factory, session, 
        		eventId );
        presenter.view().getHeaderText().setText( "Audit" );
        presenter.listView().getHeaderText().setText( "Audit List" );
    }
}
