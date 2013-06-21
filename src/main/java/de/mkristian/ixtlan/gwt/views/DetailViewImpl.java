package de.mkristian.ixtlan.gwt.views;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;

public class DetailViewImpl implements DetailView {

	protected LayoutPanel main;
	protected ScrollPanel scrollPanel;
	protected HeaderPanel headerPanel;
	protected HeaderButton backButton;
    protected HeaderButton mainButton;
	protected HeaderButton actionButton;
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

        logoutButton = new HeaderButton();
        logoutButton.setText( "Logout" );
        logoutButton.setRoundButton( true );

		mainButton = new HeaderButton();
		mainButton.setRoundButton( true );

		actionButton = new HeaderButton();
		actionButton.setRoundButton( true );
		actionButton.setVisible( false );

		FlowPanel left = new FlowPanel();
		if ( !MGWT.getOsDetection().isPhone() ) {
			left.add( mainButton );
			mainButton.addStyleName( MGWTStyle.getTheme().getMGWTClientBundle().getUtilCss().portraitonly() );
		} else {
			left.add( backButton );
		}
		left.add( actionButton );
		headerPanel.setLeftWidget( left );
		headerPanel.setRightWidget( logoutButton );

		main.add( headerPanel );
		main.add( scrollPanel );
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
	public HasTapHandlers getActionButton() {
		return actionButton;
	}

	@Override
	public HasText getActionButtonText() {
		return actionButton;
	}
}
