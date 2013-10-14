package de.mkristian.ixtlan.gwt.client.configuration;


import com.google.gwt.place.shared.PlaceController;

import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.session.SessionFacade;
import de.mkristian.ixtlan.gwt.singleton.AbstractSingletonActivity;
import de.mkristian.ixtlan.gwt.singleton.SingletonFactory;
import de.mkristian.ixtlan.gwt.singleton.SingletonPresenter;

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
