package de.mkristian.ixtlan.gwt.client.errors;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.NumberLabel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

import de.mkristian.ixtlan.gwt.ui.EnabledEditor;
import de.mkristian.ixtlan.gwt.utils.IxtlanGWTStyle;
import de.mkristian.ixtlan.gwt.utils.ViewCss;


public class ErrorEditor extends EnabledEditor<Error>{
    
    interface Binder extends UiBinder<Widget, ErrorEditor> {}

    private static final Binder BINDER = GWT.create(Binder.class);
    
    @Ignore @UiField FlowPanel signature;

    @UiField NumberLabel<Integer> id;
    @UiField DateLabel createdAt;

    @UiField MTextBox message;

    public ErrorEditor() {
      this( IxtlanGWTStyle.getIxtlanGWTClientBundle().getViewCss() );
    }
    
    public ErrorEditor( ViewCss css ) {
        css.ensureInjected();
        initWidget( BINDER.createAndBindUi( this ) );
        signature.setStylePrimaryName( css.signature() );
    }

    public void setEnabled(boolean enabled) {
        this.message.setReadOnly( !enabled );
    }
}