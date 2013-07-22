package de.mkristian.ixtlan.gwt.views;

import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.EDIT;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarSpacer;

import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.places.SingletonFactory;
import de.mkristian.ixtlan.gwt.session.Guard;
import de.mkristian.ixtlan.gwt.ui.EnabledEditor;

public abstract class SingletonViewImpl<T>
	extends DetailViewImpl
	implements SingletonView<T> {

    //private final HeaderButton reloadButton;
    private final HeaderButton editButton;
    private final HeaderButton cancelButton;
    private final HeaderButton saveButton;

    private final EnabledEditor<T> editor;
    
    protected final Guard guard;
    protected final SimpleBeanEditorDriver<T, Editor<T>> editorDriver;
    protected final SingletonFactory<T, ?> factory;
    
    public SingletonViewImpl( final Guard guard,
            final EnabledEditor<T> editor,
            final SimpleBeanEditorDriver<T, Editor<T>> driver,
            SingletonFactory<T, ?> factory ) {
        this.guard = guard;
        this.editor = editor;      
        this.factory = factory;
        this.editorDriver = driver;
        this.editorDriver.initialize( editor );
        
        editButton = new HeaderButton();
        editButton.setText( "Edit" );
        
        saveButton = new HeaderButton();
        saveButton.setText( "Save" );
        
        cancelButton = new HeaderButton();
        cancelButton.setText( "Cancel" );

        ButtonBar footer = new ButtonBar();
        footer.add( reloadButton );
        footer.add( new ButtonBarSpacer() );
        footer.add( editButton );
        footer.add( saveButton );
        footer.add( cancelButton );
        footer.add( new ButtonBarSpacer() );
        
        main.add( footer );
        
        setWidget( editor );
    }

    protected boolean isAllowed( RestfulActionEnum action ){
        return guard.isAllowed( factory.placeName(), action );
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
    
    private void setupButtons( boolean editable ) {
        editButton.setVisible( false );
        saveButton.setVisible( false );
        cancelButton.setVisible( false );
        editor.setEnabled( editable );
    }

    @Override
    public void edit() {
        setupButtons( true );        
        saveButton.setVisible( isAllowed( EDIT ) );
        cancelButton.setVisible( true );
    }

    @Override
    public void show() {
        setupButtons( false );  
        editButton.setVisible( isAllowed( EDIT ) );
    }
    
    @Override
    public void reset( T model ) {
        editorDriver.edit( model );
    }

    @Override
    public T flush(){
    	return editorDriver.flush();
    }

    @Override
    public boolean isDirty() {
        return editorDriver.isDirty();
    }

}