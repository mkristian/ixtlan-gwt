package de.mkristian.ixtlan.gwt.crud;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarSpacer;
import com.googlecode.mgwt.ui.client.widget.buttonbar.TrashButton;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.singleton.SingletonViewImpl;
import de.mkristian.ixtlan.gwt.ui.EnabledEditor;

public class CRUDViewImpl<T extends Identifiable> 
            extends SingletonViewImpl<T>
            implements CRUDView<T> {

    protected ButtonBase createButton;
    protected ButtonBase deleteButton;

    public CRUDViewImpl( final EnabledEditor<T> editor,
            final SimpleBeanEditorDriver<T, Editor<T>> driver ) {
        super( editor, driver );

        createButton.setText( "Create" );
        
		listAllButton.setVisible( true );
        newButton.setVisible( true );
    }

    @Override
    protected ButtonBar createFooter()
    {
        createButton = new Button();
        deleteButton = new TrashButton();
        ButtonBar footer = new ButtonBar();     
        footer.add( newButton );
        footer.add( reloadButton );
        footer.add( new ButtonBarSpacer() );
        footer.add( createButton );
        footer.add( editButton );
        footer.add( saveButton );
        footer.add( cancelButton );
        footer.add( new ButtonBarSpacer() );
        footer.add( deleteButton );
        return footer;
    }

    protected void setupButtons( boolean editable ) {
        super.setupButtons( editable );
        newButton.setVisible( true );
        createButton.setVisible( false );
        deleteButton.setVisible( false );
    }

    @Override
    public void edit( RestfulActionEnum permission ) {
        setupButtons( true );   
        cancelButton.setVisible( true );
        switch( permission ){
        case DESTROY:
            deleteButton.setVisible( true );
        case NEW:
            newButton.setVisible( true );
        case EDIT:
            saveButton.setVisible( true );
        default:
        }
    }

    @Override
    public void show(RestfulActionEnum permission) {
        setupButtons( false );
        switch( permission ){
        case DESTROY:
            deleteButton.setVisible( true );
        case NEW:
            newButton.setVisible( true );
        case EDIT:
            editButton.setVisible( true );
        default:
        }
    }

    @Override
    public void create(RestfulActionEnum permission) {
        setupButtons( true );  
        newButton.setVisible( false );
        reloadButton.setVisible( false );
        cancelButton.setVisible( true );
        switch( permission ){
        case DESTROY:
        case NEW:
            createButton.setVisible( true );
        default:
        }
    }

    @Override
    public HasTapHandlers getCreateButton() {
    	return this.createButton;
    }

    @Override
    public HasTapHandlers getDeleteButton() {
    	return this.deleteButton;
    }
}