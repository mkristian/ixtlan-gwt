package de.mkristian.ixtlan.gwt.models;


import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.NumberLabel;
import com.google.gwt.user.client.ui.Widget;

import de.mkristian.ixtlan.gwt.ui.EnabledEditor;
import de.mkristian.ixtlan.gwt.ui.UserLabel;
import de.mkristian.ixtlan.gwt.utils.IxtlanGWTStyle;
import de.mkristian.ixtlan.gwt.utils.ViewCss;

public class AbstractModelEditor<T extends AbstractModel>
		extends EnabledEditor<T> {
    
    @Ignore @UiField public FlowPanel signature;

    @UiField public NumberLabel<Integer> id;
    @UiField public DateLabel createdAt;
    @UiField public DateLabel updatedAt;
    @UiField public UserLabel modifiedBy;

	private final ViewCss css;
    
    public AbstractModelEditor() {
        this( IxtlanGWTStyle.getIxtlanGWTClientBundle().getViewCss() );
    }
 
    public AbstractModelEditor( ViewCss css ) {
        css.ensureInjected();
        this.css = css;
    }

    
    @Override
	protected void initWidget( Widget widget ) {
    	// TODO maybe pass in the widget through the constructor
		super.initWidget( widget );
        signature.setStylePrimaryName( css.signature() );
	}

	public void setEnabled( boolean enabled ) {
        this.signature.setVisible( id.getValue() != null && id.getValue() != 0 );
    }
}
