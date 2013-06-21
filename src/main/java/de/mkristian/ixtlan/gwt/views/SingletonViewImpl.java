package de.mkristian.ixtlan.gwt.views;

import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.EDIT;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.FlowPanel;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;

import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.places.SingletonFactory;
import de.mkristian.ixtlan.gwt.session.Guard;
import de.mkristian.ixtlan.gwt.ui.EnabledEditor;

public abstract class SingletonViewImpl<T>
	extends DetailViewImpl
	implements SingletonView<T> {

    private HeaderButton reloadButton;
    private HeaderButton editButton;
    private HeaderButton cancelButton;
    private HeaderButton saveButton;

    private EnabledEditor<T> editor;
    
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
        
        // setup widget
        reloadButton = new HeaderButton();
        reloadButton.setText( "Reload" );
        
        editButton = new HeaderButton();
        editButton.setText( "Edit" );
        
        saveButton = new HeaderButton();
        saveButton.setText( "Save" );
        
        cancelButton = new HeaderButton();
        cancelButton.setText( "Cancel" );

        ButtonBar bar = new ButtonBar();        
        bar.add( reloadButton );
        bar.add( editButton );
        bar.add( saveButton );
        bar.add( cancelButton );
        
        FlowPanel main = new FlowPanel();
        main.add( bar );
        main.add( editor );
        
        setWidget( main );
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
    
    private void setupButtons( boolean editable, T model ) {
        editButton.setVisible( false );
        saveButton.setVisible( false );
        cancelButton.setVisible( false );
        editorDriver.edit( model );
        editor.setEnabled( editable );
    }

    public void edit( T model ) {
        setupButtons( true, model );        
        saveButton.setVisible( isAllowed( EDIT ) );
        cancelButton.setVisible( true );
    }

    public void show( T model ) {
        setupButtons( false, model );  
        editButton.setVisible( isAllowed( EDIT ) );
    }

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