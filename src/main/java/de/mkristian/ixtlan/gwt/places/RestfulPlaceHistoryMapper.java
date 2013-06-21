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

public class RestfulPlaceHistoryMapper extends PlaceWithSessionHistoryMapper {

    private final Map<String, RestfulPlaceTokenizer<?>> map = new HashMap<String, RestfulPlaceTokenizer<?>>();
    
    @Inject
    public RestfulPlaceHistoryMapper( EventBus eventBus ){
        super( eventBus );
    }
    
    protected void register(String key, RestfulPlaceTokenizer<?> tokenizer) {
        this.map.put(key, tokenizer);
    }

    public PlaceWithSession getPlaceWithoutSession(String token) {
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
            return tokenizer.getPlace(subtoken);
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