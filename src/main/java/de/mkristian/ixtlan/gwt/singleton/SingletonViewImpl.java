package de.mkristian.ixtlan.gwt.singleton;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarSpacer;

import de.mkristian.ixtlan.gwt.common.DetailViewImpl;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.ui.EnabledEditor;

public abstract class SingletonViewImpl<T>
	extends DetailViewImpl
	implements SingletonView<T> {

    protected final ButtonBase editButton = new Button();
    protected final ButtonBase cancelButton = new Button();
    protected final ButtonBase saveButton = new Button();

    protected final EnabledEditor<T> editor;
    protected final SimpleBeanEditorDriver<T, Editor<T>> editorDriver;
    
    public SingletonViewImpl( final EnabledEditor<T> editor,
            final SimpleBeanEditorDriver<T, Editor<T>> driver ){
        this.editor = editor;      
        this.editorDriver = driver;
        this.editorDriver.initialize( editor );
        
        editButton.setText( "Edit" );
        
        saveButton.setText( "Save" );
        
        cancelButton.setText( "Cancel" );

        setWidget( editor );
        main.add( createFooter() );
    }

    protected ButtonBar createFooter()
    {
        ButtonBar footer = new ButtonBar();
        footer.add( reloadButton );
        footer.add( new ButtonBarSpacer() );
        footer.add( editButton );
        footer.add( saveButton );
        footer.add( cancelButton );
        footer.add( new ButtonBarSpacer() );
        return footer;
    }
    
    protected void setupButtons( boolean editable ) {
        reloadButton.setVisible( true );
        editButton.setVisible( false );
        saveButton.setVisible( false );
        cancelButton.setVisible( false );
        editor.setEnabled( editable );
    }

    @Override
    public void edit( RestfulActionEnum permission ) {
        setupButtons( true );   
        cancelButton.setVisible( true );
        switch( permission ){
        case EDIT:
            saveButton.setVisible( true );
        default:
        }
    }

    @Override
    public void show(RestfulActionEnum permission) {
        setupButtons( false );
        switch( permission ){
        case EDIT:
            editButton.setVisible( true );
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
    public T flush(){
    	return editorDriver.flush();
    }

    @Override
    public boolean isDirty() {
        return editorDriver.isDirty();
    }
    
    @Override
    public HasTapHandlers getCancelButton() {
        return this.cancelButton;
    }
    
    @Override
    public HasTapHandlers getReloadButton() {
        return this.reloadButton;
    }
    
    @Override
    public HasTapHandlers getEditButton() {
        return this.editButton;
    }
    
    @Override
    public HasTapHandlers getSaveButton() {
        return this.saveButton;
    }
}