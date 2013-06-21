package de.mkristian.ixtlan.gwt.errors;


import com.google.gwt.place.shared.PlaceController;

import de.mkristian.ixtlan.gwt.activities.AbstractReadOnlyActivity;
import de.mkristian.ixtlan.gwt.places.Factory;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.session.SessionFacade;

public class AbstractErrorActivity extends AbstractReadOnlyActivity<Error> {
	
    public AbstractErrorActivity( RestfulPlace<Error, ?> place,
    		ErrorPresenter presenter,
    		PlaceController places,
    		Factory<Error, ?> factory,
    		SessionFacade session, 
    		String eventId ) {
        super( place, presenter, places, factory, session, 
        		eventId );
        presenter.view().getHeaderText().setText( "Error" );
        presenter.listView().getHeaderText().setText( "Error List" );
    }
}
