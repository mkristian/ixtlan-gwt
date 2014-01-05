package de.mkristian.ixtlan.gwt.common;


import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlexTable;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulAction;

public class ListWidget<T extends Identifiable> extends FlexTable {

    private HandlerRegistrationCollection handlerRegistrationCollection;

	private TapHandler tapHandler;
	
	public HandlerRegistration setTapHandler(TapHandler handler) {
    	this.handlerRegistrationCollection = new HandlerRegistrationCollection();
    	this.tapHandler = handler;
    	return handlerRegistrationCollection;
	}
	
	protected ButtonBase newButton( RestfulAction action, T model ) {
        ModelButton<T> button = new ModelButton<T>( action, model );
        handlerRegistrationCollection.addHandlerRegistration( button.addTapHandler( tapHandler ) );
        return button;
    }
}