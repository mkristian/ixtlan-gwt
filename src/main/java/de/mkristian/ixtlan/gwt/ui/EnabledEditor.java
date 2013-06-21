package de.mkristian.ixtlan.gwt.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Composite;

public class EnabledEditor<T> extends Composite
            implements Editor<T> {
    
    public void setEnabled( boolean enabled ) {
        GWT.log("please overwrite 'setEnabled()'" );
    }
}