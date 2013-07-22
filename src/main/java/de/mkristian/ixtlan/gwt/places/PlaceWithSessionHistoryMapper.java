package de.mkristian.ixtlan.gwt.places;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.session.Session;
import de.mkristian.ixtlan.gwt.session.SessionEvent;
import de.mkristian.ixtlan.gwt.session.SessionEventHandler;
import de.mkristian.ixtlan.gwt.session.User;

public abstract class PlaceWithSessionHistoryMapper implements PlaceHistoryMapper{

    private User currentUser = null;
    
    public PlaceWithSessionHistoryMapper( EventBus eventBus ){
        eventBus.addHandler( SessionEvent.TYPE, new SessionEventHandler() {
            
            @Override
            public void onModelEvent(ModelEvent<Session> event) {
                switch( event.getAction() ){
                    case CREATE:
                        currentUser = event.getModel().user;
                        break;
                    case DESTROY:
                        currentUser = null;
                        break;
                    default:
                    	break;
                }
                
            }
        });
    }
    @Override
    public Place getPlace( String token ) {
        PlaceWithSession place = getPlaceWithoutSession(token);
        if ( place != null ){
            place.currentUser = currentUser;
        }
        return place;
    }

    abstract public PlaceWithSession getPlaceWithoutSession( String token );
    
}