package de.mkristian.ixtlan.gwt.models;


import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.NumberLabel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.base.MValueBoxBase;

import de.mkristian.ixtlan.gwt.ui.EnabledEditor;
import de.mkristian.ixtlan.gwt.ui.UserLabel;
import de.mkristian.ixtlan.gwt.utils.IxtlanGWTStyle;
import de.mkristian.ixtlan.gwt.utils.ViewCss;

public class AbstractModelEditor<T extends AbstractModel>
		extends EnabledEditor<T> {
    
    public static final String IMMUTABLE = " (immutable)";

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

    protected boolean isNew()
    {
        return id.getValue() == null ? true : 
            ( id.getValue() == 0 ? true : false );
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

    protected void ensureImmutableText( HasText text )
    {
        String t = text.getText();
        if( !t.contains( IMMUTABLE ) ){
            text.setText( t + IMMUTABLE );
        }
    }

    protected void ensureMutableText( HasText text )
    {
        String t = text.getText();
        if( t.contains( IMMUTABLE ) ){
            text.setText( t.replace( IMMUTABLE,
                          "" ) );
        }
    }
    protected void ensureImmutable( boolean enabled, HasEnabled input, HasText label  )
    {
        input.setEnabled( enabled && isNew() );
        doEnsureImmutable( enabled, label );
    }
    
    protected void ensureImmutable( boolean enabled, MValueBoxBase<String> input, HasText label )
    {
        input.setReadOnly( !enabled || !isNew() );
        doEnsureImmutable( enabled, label );
    }

    private void doEnsureImmutable( boolean enabled, HasText label )
    {
        if( enabled && !isNew() ){
            ensureImmutableText( label );
        }
        if( !enabled || ( enabled && isNew() ) ){
            ensureMutableText( label );
        }
    }
}
