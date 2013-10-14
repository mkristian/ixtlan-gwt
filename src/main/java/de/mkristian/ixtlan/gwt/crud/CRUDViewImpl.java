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

public abstract class CRUDViewImpl<T extends Identifiable> 
            extends SingletonViewImpl<T>
            implements CRUDView<T> {

//    private final ButtonBase editButton = new Button();
//    private final ButtonBase cancelButton = new Button();
    protected ButtonBase createButton;
//    private final ButtonBase saveButton = new Button();
    protected ButtonBase deleteButton;

//    private final EnabledEditor<T> editor;
//    private final SimpleBeanEditorDriver<T, Editor<T>> editorDriver;
//
    public CRUDViewImpl( final EnabledEditor<T> editor,
            final SimpleBeanEditorDriver<T, Editor<T>> driver ) {
        super( editor, driver );
//        this.editor = editor;  
//        this.editorDriver = driver;
//        this.editorDriver.initialize( editor );
        
        createButton.setText( "Create" );
//
//        cancelButton.setText( "Cancel" );
//
//        editButton.setText( "Edit" );
//        
//        saveButton.setText( "Save" );
//        
//        setWidget( editor );
//        main.add( createFooter() );
        
		listAllButton.setVisible( true );
        newButton.setVisible( true );
    }

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
//        reloadButton.setVisible( true );
        newButton.setVisible( true );
        createButton.setVisible( false );
  //      editButton.setVisible( false );
    //    saveButton.setVisible( false );
      //  cancelButton.setVisible( false );
        deleteButton.setVisible( false );
        //editor.setEnabled( editable );
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
//        saveButton.setVisible( false );
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