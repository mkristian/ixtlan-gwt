package de.mkristian.ixtlan.gwt.singleton;

import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.places.RestfulPlaceTokenizer;


public abstract class SingletonRestfulPlaceTokenizer<P extends RestfulPlace<?, ?>>
        extends RestfulPlaceTokenizer<P> {

    public P getPlace(String token) {
        return newRestfulPlace( toSingletonToken(token).action );
    }

    protected RestfulPlaceTokenizer.Token toSingletonToken(String token){
        if(token.endsWith(SEPARATOR)){
            token = token.substring(0, token.length() - 1);
        }
        RestfulAction action = toRestfulAction(token);
        if(action == null){
            action = RestfulActionEnum.SHOW;
        }
        return new Token(action);
    }
}