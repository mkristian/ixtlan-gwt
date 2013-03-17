/**
 *
 */
package de.mkristian.ixtlan.gwt.utils;

import com.google.gwt.user.client.ui.Label;
import com.google.inject.Singleton;

@Singleton
public class NetworkIndicatorLabel extends Label implements NetworkIndicator {

    public NetworkIndicatorLabel() {
        this( IxtlanGWTStyle.getIxtlanGWTClientBundle().getNetworkIndicatorCss() );
    }
    
    public NetworkIndicatorLabel(  NetworkIndicatorCss css ) {
        css.ensureInjected();
        setStylePrimaryName( css.loading() );
        setVisible( false );
    }
    
    public void loading(){
        setText( "loading" );
        setVisible( true );
    }

    public void saving(){
        setText( "saving" );
        setVisible( true );
    }
    
    public void deleting(){
        setText( "deleting" );
        setVisible( true );
    }
    
    public void finished(){
        setVisible( false );
    }
}