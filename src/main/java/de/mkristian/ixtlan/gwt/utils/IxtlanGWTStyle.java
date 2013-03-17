package de.mkristian.ixtlan.gwt.utils;

import com.google.gwt.core.client.GWT;

public class IxtlanGWTStyle {
    
    private static IxtlanGWTClientBundle bundle;
    public static IxtlanGWTClientBundle getIxtlanGWTClientBundle(){
        if (bundle == null){
            bundle = GWT.create( IxtlanGWTClientBundle.class );
        }
        return bundle;
    }
}