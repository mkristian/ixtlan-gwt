package de.mkristian.ixtlan.gwt.views;

import com.google.gwt.event.shared.HandlerRegistration;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;

public interface DetailListView extends DetailView {
	HandlerRegistration setTapHandler( TapHandler handler );
}