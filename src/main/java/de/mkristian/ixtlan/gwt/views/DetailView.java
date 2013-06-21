package de.mkristian.ixtlan.gwt.views;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

public interface DetailView extends IsWidget{
	
	public HasTapHandlers getActionButton();
    
    public HasTapHandlers getBackbutton();

    public HasTapHandlers getMainButton();

    public HasTapHandlers getLogoutButton();
    
    public HasText getHeaderText();
	
	public HasText getActionButtonText();
	
	public HasText getMainButtonText();

	public HasText getBackbuttonText();

}
