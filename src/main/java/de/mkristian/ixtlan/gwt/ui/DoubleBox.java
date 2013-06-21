/**
 * 
 */
package de.mkristian.ixtlan.gwt.ui;

import com.google.gwt.text.client.DoubleParser;
import com.google.gwt.text.client.DoubleRenderer;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBox;

public class DoubleBox extends ValueBox<Double> {

    public DoubleBox() {
        super(new TextBox().getElement(), DoubleRenderer.instance(), DoubleParser.instance());
    }
    
}