/**
 * 
 */
package de.mkristian.ixtlan.gwt.ui;

import com.google.gwt.text.client.IntegerParser;
import com.google.gwt.text.client.IntegerRenderer;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBox;

public class IntegerBox extends ValueBox<Integer> {

    public IntegerBox() {
        super(new TextBox().getElement(), IntegerRenderer.instance(), IntegerParser.instance());
    }
    
}