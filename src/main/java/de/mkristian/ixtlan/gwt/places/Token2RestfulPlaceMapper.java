package de.mkristian.ixtlan.gwt.places;

import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.INDEX;
import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.SHOW;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.google.gwt.core.shared.GWT;

import de.mkristian.ixtlan.gwt.readonly.ReadonlyFactory;
import de.mkristian.ixtlan.gwt.singleton.SingletonFactory;

@Singleton
public class Token2RestfulPlaceMapper {   
    
    public static final String SEPARATOR = "/";
    public static final String QUERY_SEPARATOR = "$";

    private final Map<String, ReadonlyFactory<?, ?>> identifiableMap = new HashMap<String, ReadonlyFactory<?, ?>>();
    private final Map<String, SingletonFactory<?, ?>> singletonMap = new HashMap<String, SingletonFactory<?, ?>>();
    
    public void register( SingletonFactory<?, ?> factory ){
        singletonMap.put( factory.placeName(), factory );
    }
    public void register( ReadonlyFactory<?, ?> factory ){
        identifiableMap.put( factory.placeName(), factory );
    }
    
    public RestfulPlace<?, ?> toPlace( String token ){
        String[] parts = token.replaceFirst( QUERY_SEPARATOR + ".*$", "" )
                              .replaceFirst( "^" + SEPARATOR, "" )
                              .split( SEPARATOR );
        GWT.log( Arrays.toString( parts  ) );
        return toPlace( null, 0, parts );
    }
     
    private RestfulPlace<?, ?> toPlace( RestfulPlace<?,?> parent, int index, String... parts ){
        GWT.log( "parent " + parent );
        RestfulPlace<?, ?> result;
        if( identifiableMap.containsKey( parts[ index ] ) ){
            result = identifiablePlace( index, identifiableMap.get( parts[ index ] ), parts );
        }
        else if( singletonMap.containsKey( parts[ index ] ) ){
            result = singletonPlace( index, singletonMap.get( parts[ index ] ), parts );
        }
        else {
            return null;
        }
        if( result.getParent() == null && parent != null ){
            result.setParent( parent );
        }
        GWT.log( "result " +  result);
        return result;
    }
    
    private RestfulPlace<?, ?> identifiablePlace( int index,
                                                  ReadonlyFactory<?, ?> factory,
                                                  String... parts )
    {
        switch( parts.length - index ){
        case 1:
            return factory.newRestfulPlace( INDEX );
        case 2:
            RestfulActionEnum action = RestfulActionEnum.fromToken( parts[ index + 1 ] );
            if ( action != null ){
                return factory.newRestfulPlace( action );
            }
            return factory.newRestfulPlace( Integer.parseInt( parts[ index + 1 ] ), SHOW );
        case 3:
            action = RestfulActionEnum.fromToken( parts[ index + 2 ] );
            if ( action != null ){
                return factory.newRestfulPlace( Integer.parseInt( parts[ index + 1 ] ), action );
            }
        default:
            RestfulPlace<?,?> place = factory.newRestfulPlace( Integer.parseInt( parts[ index + 1 ] ),
                                                               SHOW );
            return toPlace( place, index + 2, parts );
        }
    }
    
    private RestfulPlace<?, ?> singletonPlace( int index,
                                               SingletonFactory<?, ?> factory,
                                               String... parts )
    {
        switch( parts.length - index ){
        case 1:
            return factory.newRestfulPlace( SHOW );
        case 2:
            RestfulActionEnum action = RestfulActionEnum.fromToken( parts[ index + 1 ] );
            if ( action != null ){
                return factory.newRestfulPlace( action );
            }
        default:
            RestfulPlace<?,?> place = factory.newRestfulPlace( SHOW );
            return toPlace( place, index + 1, parts );
        }
    }
}