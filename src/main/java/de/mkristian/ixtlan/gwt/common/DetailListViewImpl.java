package de.mkristian.ixtlan.gwt.common;

import com.google.gwt.event.shared.HandlerRegistration;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;

import de.mkristian.ixtlan.gwt.models.Identifiable;

public abstract class DetailListViewImpl<T extends Identifiable>
	extends DetailViewImpl
	implements DetailListView {
	
	protected final ListWidget<T> list;

    public DetailListViewImpl( ListWidget<T> list ){
    	this.list = list;
        setWidget( list );
    }
    
	
    @Override
	public HandlerRegistration setTapHandler(TapHandler handler) {
    	return list.setTapHandler( handler );
	}

}