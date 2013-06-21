package de.mkristian.ixtlan.gwt.configuration;


import com.google.gwt.place.shared.PlaceController;

import de.mkristian.ixtlan.gwt.activities.AbstractSingletonActivity;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.places.SingletonFactory;
import de.mkristian.ixtlan.gwt.presenters.SingletonPresenter;
import de.mkristian.ixtlan.gwt.session.SessionFacade;

public class AbstractConfigurationActivity<T extends Configuration>
		extends AbstractSingletonActivity<T> {

    public AbstractConfigurationActivity( RestfulPlace<T, ?> place,
    		SingletonPresenter<T> presenter,
    		PlaceController places,
            SingletonFactory<T, ?> factory,
    		SessionFacade session,
    		String eventId ) {
        super( place, presenter, places, factory, session, eventId );
        presenter.view().getHeaderText().setText( "Configuration" );
    }
}
