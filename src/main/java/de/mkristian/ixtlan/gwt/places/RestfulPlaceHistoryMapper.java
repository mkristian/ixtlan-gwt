/**
 *
 */
package de.mkristian.ixtlan.gwt.places;

import static de.mkristian.ixtlan.gwt.places.QueryableRestfulPlaceTokenizer.QUERY_SEPARATOR;
import static de.mkristian.ixtlan.gwt.places.RestfulPlaceTokenizer.SEPARATOR;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.session.Session;
import de.mkristian.ixtlan.gwt.session.SessionEvent;
import de.mkristian.ixtlan.gwt.session.SessionEventHandler;

public class RestfulPlaceHistoryMapper implements PlaceHistoryMapper {

    private final Map<String, RestfulPlaceTokenizer<?>> map = new HashMap<String, RestfulPlaceTokenizer<?>>();
    private boolean activeSession = false;
    
    @Inject
    public RestfulPlaceHistoryMapper( EventBus eventBus ){
        eventBus.addHandler( SessionEvent.TYPE, new SessionEventHandler() {
            
            @Override
            public void onModelEvent(ModelEvent<Session> event) {
                switch( event.getAction() ){
                    case CREATE:
                        activeSession = true;
                        break;
                    case DESTROY:
                        activeSession = false;
                        break;
                }
                
            }
        });
    }
    
    protected void register(String key, RestfulPlaceTokenizer<?> tokenizer) {
        this.map.put(key, tokenizer);
    }

    public Place getPlace(String token) {
        // name => ""
        // name/ => ""
        // name$query => $query
        // name/something => "something"
        // name/something$query => something$query
        final String key;
        final String subtoken;
        int queryStart = token.indexOf(QUERY_SEPARATOR);
        if(!token.contains(SEPARATOR)){
            if(queryStart > 0){
                key = token.substring(0, queryStart);
            }
            else {
                key = token;
            }
            subtoken = token.substring(key.length());
        }
        else {
            key = token.substring(0, token.indexOf(SEPARATOR));
            subtoken = token.substring(key.length() + 1);
        }
        RestfulPlaceTokenizer<?> tokenizer = this.map.get(key);
        if (tokenizer == null) {
            return null;
        } else {
            RestfulPlace<?, ?> place = tokenizer.getPlace(subtoken);
            if (place != null) {
                // place needs to be different on the level of equals in order to trigger an activity
                place.hasSession = activeSession;
            }
            return place;
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public String getToken(Place place) {
        RestfulPlaceTokenizer tokenizer = this.map
                .get(((RestfulPlace) place).resourceName);
        if (tokenizer == null) {
            return null;
        } else {
            return tokenizer.getToken((RestfulPlace) place);
        }
    }
}