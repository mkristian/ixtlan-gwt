/**
 *
 */
package de.mkristian.ixtlan.gwt.views;

import com.googlecode.mgwt.ui.client.widget.HeaderButton;

import de.mkristian.ixtlan.gwt.places.RestfulAction;

public class GeneralModelButton<T, S extends RestfulAction> extends HeaderButton {
    public final T model;
    public final S action;
    
    public GeneralModelButton(S action, T model){
        super();
        setText( action.name().substring( 0, 1 ).toUpperCase() +
        			action.name().substring( 1 ).toLowerCase() );
        this.model = model;
        this.action = action;
    }
}