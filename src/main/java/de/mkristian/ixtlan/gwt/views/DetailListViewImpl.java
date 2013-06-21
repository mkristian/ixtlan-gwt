package de.mkristian.ixtlan.gwt.views;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlexTable;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;

public abstract class DetailListViewImpl<T extends Identifiable>
	extends DetailViewImpl
	implements DetailListView {
    
    protected final FlexTable list = new FlexTable();

    public DetailListViewImpl( ){
		actionButton.setVisible( false );
        setWidget( list );
    }
    
    private HandlerRegistrationCollection handlerRegistrationCollection;

	private TapHandler tapHandler;
	
    @Override
	public HandlerRegistration setTapHandler(TapHandler handler) {
    	this.handlerRegistrationCollection = new HandlerRegistrationCollection();
    	this.tapHandler = handler;
    	return handlerRegistrationCollection;
	}

	protected ButtonBase newButton( RestfulActionEnum action, T model ) {
        ModelButton<T> button = new ModelButton<T>( action, model );
        handlerRegistrationCollection.addHandlerRegistration( button.addTapHandler( tapHandler ) );
        return button;
    }
}