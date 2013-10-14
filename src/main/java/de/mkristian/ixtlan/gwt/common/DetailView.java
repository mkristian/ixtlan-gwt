package de.mkristian.ixtlan.gwt.common;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

public interface DetailView extends IsWidget{
	
	HasTapHandlers getListAllButton();

	HasTapHandlers getNewButton();

	HasTapHandlers getReloadButton();
	
	HasTapHandlers getBackbutton();

	HasTapHandlers getMainButton();

	HasTapHandlers getLogoutButton();
    
	HasText getHeaderText();
	
	HasText getListAllButtonText();
	
	HasText getMainButtonText();

	HasText getBackbuttonText();

}
