package de.mkristian.ixtlan.gwt.views;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarSpacer;
import com.googlecode.mgwt.ui.client.widget.buttonbar.TrashButton;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.ui.EnabledEditor;

public abstract class CRUDViewImpl<T extends Identifiable> 
            extends DetailViewImpl
            implements CRUDView<T> {

    private final ButtonBase editButton;
    private final ButtonBase cancelButton;
    private final ButtonBase createButton;
    private final ButtonBase saveButton;
    private final ButtonBase deleteButton;

    private final EnabledEditor<T> editor;
    private final SimpleBeanEditorDriver<T, Editor<T>> editorDriver;

    public CRUDViewImpl( final EnabledEditor<T> editor,
            final SimpleBeanEditorDriver<T, Editor<T>> driver ) {
        this.editor = editor;  
        this.editorDriver = driver;
        this.editorDriver.initialize( editor );
        
        // setup widget
        createButton = new HeaderButton();
        createButton.setText( "Create" );

        cancelButton = new HeaderButton();
        cancelButton.setText( "Cancel" );

        editButton = new HeaderButton();
        editButton.setText( "Edit" );
        
        saveButton = new HeaderButton();
        saveButton.setText( "Save" );
        
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
        
        setWidget( editor );
        main.add( footer );
        
		listAllButton.setVisible( true );
        newButton.setVisible( true );
    }

    private void setupButtons( boolean editable ) {
        reloadButton.setVisible( true );
        newButton.setVisible( true );
        createButton.setVisible( false );
        editButton.setVisible( false );
        saveButton.setVisible( false );
        cancelButton.setVisible( false );
        deleteButton.setVisible( false );
        editor.setEnabled( editable );
    }

    @Override
    public void edit(RestfulActionEnum permission) {
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
    public void reset( T model ) {
        editorDriver.edit( model );
        // TODO maybe better way to decide the editing mode !!
        editor.setEnabled( cancelButton.isVisible() );
    }

    @Override
    public boolean isDirty() {
        return editorDriver.isDirty();
    }

    @Override
    public T flush() {
        return editorDriver.flush();
    }

    @Override
    public HasTapHandlers getCreateButton() {
    	return this.createButton;
    }

    @Override
    public HasTapHandlers getCancelButton() {
    	return this.cancelButton;
    }
    
	@Override
	public HasTapHandlers getEditButton() {
    	return this.editButton;
	}

	@Override
    public HasTapHandlers getSaveButton() {
    	return this.saveButton;
    }

    @Override
    public HasTapHandlers getDeleteButton() {
    	return this.deleteButton;
    }
}