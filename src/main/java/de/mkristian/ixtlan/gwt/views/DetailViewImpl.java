package de.mkristian.ixtlan.gwt.views;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;
import com.googlecode.mgwt.ui.client.widget.buttonbar.NewIconButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.RefreshButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel.TabBar;

public class DetailViewImpl implements DetailView {

	protected LayoutPanel main;
	protected ScrollPanel scrollPanel;
	protected HeaderPanel headerPanel;
	protected HeaderButton backButton;
    protected HeaderButton mainButton;
	protected HeaderButton listAllButton;
	protected ButtonBase newButton;
	protected ButtonBase reloadButton;
	protected HeaderButton logoutButton;
	protected final HTML title = new HTML();

	public DetailViewImpl() {
		main = new LayoutPanel();

		scrollPanel = new ScrollPanel();

		headerPanel = new HeaderPanel();
		headerPanel.setCenterWidget( title );
		
		backButton = new HeaderButton();
		backButton.setBackButton( true );
		backButton.setVisible( !MGWT.getOsDetection().isAndroid() );

		newButton = new NewIconButton();
        newButton.setVisible( false );

		reloadButton = new RefreshButton();
		
        logoutButton = new HeaderButton();
        logoutButton.setText( "Logout" );
        logoutButton.setRoundButton( true );

		mainButton = new HeaderButton();
		mainButton.setRoundButton( true );

		listAllButton = new HeaderButton();
		listAllButton.setText( "List All" );
		listAllButton.setRoundButton( true );
		listAllButton.setVisible( false );

		Panel left = new HorizontalPanel();
		if ( !MGWT.getOsDetection().isPhone() ) {
			left.add( mainButton );
			left.add( listAllButton );
			mainButton.addStyleName( MGWTStyle.getTheme().getMGWTClientBundle().getUtilCss().portraitonly() );
		} else {
			left.add( backButton );
			left.add( listAllButton );
		}
		headerPanel.setLeftWidget( left );		
		headerPanel.setRightWidget( logoutButton );

		main.add( headerPanel );
        TabBar tabs = createTabBar();
        if ( tabs != null ) {
        	main.add( tabs );
        }
		main.add( scrollPanel );
	}

    protected TabBar createTabBar(){
    	return null;
    }
    
	protected void setWidget( Widget widget ){
	    scrollPanel.setWidget( widget );
	}
	
	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public HasText getHeaderText() {
		return title;
	}

	@Override
	public HasText getBackbuttonText() {
		return backButton;
	}

	@Override
	public HasTapHandlers getNewButton() {
		return newButton;
	}
	
	@Override
	public HasTapHandlers getReloadButton() {
		return reloadButton;
	}
	
	@Override
	public HasTapHandlers getBackbutton() {
		return backButton;
	}
	
    @Override
    public HasTapHandlers getLogoutButton() {
        return logoutButton;
    }

	@Override
	public HasText getMainButtonText() {
		return mainButton;
	}

	@Override
	public HasTapHandlers getMainButton() {
		return mainButton;
	}

	@Override
	public HasTapHandlers getListAllButton() {
		return listAllButton;
	}

	@Override
	public HasText getListAllButtonText() {
		return listAllButton;
	}
}
