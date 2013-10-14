package de.mkristian.ixtlan.gwt.client.errors;


import com.google.gwt.place.shared.PlaceController;

import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.readonly.AbstractReadonlyActivity;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyFactory;
import de.mkristian.ixtlan.gwt.session.SessionFacade;

public class AbstractErrorActivity extends AbstractReadonlyActivity<Error> {
	
    public AbstractErrorActivity( RestfulPlace<Error, ?> place,
    		ErrorPresenter presenter,
    		PlaceController places,
    		ReadonlyFactory<Error, ?> factory,
    		SessionFacade session, 
    		String eventId ) {
        super( place, presenter, places, factory, session, 
        		eventId );
        presenter.view().getHeaderText().setText( "Error" );
        presenter.listView().getHeaderText().setText( "Error List" );
    }
}
