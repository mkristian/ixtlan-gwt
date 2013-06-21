package de.mkristian.ixtlan.gwt.utils;

import com.google.gwt.resources.client.ClientBundle;

public interface IxtlanGWTClientBundle extends ClientBundle  {

    @Source("display_message.css")
    DisplayMessageCss getDisplayMessageCss();
    
    @Source("network_indicator.css")
    NetworkIndicatorCss getNetworkIndicatorCss();
    
    @Source("view.css")
    ViewCss getViewCss();
    
}