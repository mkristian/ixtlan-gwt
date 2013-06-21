package de.mkristian.ixtlan.gwt.views;

import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.EDIT;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;

import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.places.SingletonFactory;
import de.mkristian.ixtlan.gwt.presenters.SingletonPresenter;
import de.mkristian.ixtlan.gwt.session.Guard;
import de.mkristian.ixtlan.gwt.ui.EnabledEditor;

public abstract class SingletonViewImpl<T> extends DetailViewImpl {

    @UiField public HeaderButton reloadButton;
    @UiField public HeaderButton editButton;
    @UiField public HeaderButton cancelButton;
    @UiField public HeaderButton saveButton;

    @UiField(provided = true) public EnabledEditor<T> editor;
    
    private SingletonPresenter<T> presenter;
    
    protected final Guard guard;
    protected final PlaceController places;
    protected final SimpleBeanEditorDriver<T, Editor<T>> editorDriver;
    protected final SingletonFactory<T, ?> factory;
    
    public SingletonViewImpl( final Guard guard,
            final PlaceController places, 
            final EnabledEditor<T> editor,
            final SimpleBeanEditorDriver<T, Editor<T>> driver,
            SingletonFactory<T, ?> factory ) {
        this.guard = guard;
        this.places = places; 
        this.editor = editor;      
        this.factory = factory;
        this.editorDriver = driver;
        this.editorDriver.initialize( editor );
    }

    protected boolean isAllowed( RestfulActionEnum action ){
        return guard.isAllowed( factory.placeName(), action );
    }
    
    public void setPresenter( SingletonPresenter<T> presenter ) {
        this.presenter = presenter;
    }

    @UiHandler("cancelButton")
    public void onTapCancel( TapEvent event ) {
        places.goTo( factory.newRestfulPlace( this.presenter.get(),
                RestfulActionEnum.SHOW ) );
    }

    @UiHandler("reloadButton")
    public void onTapReload( TapEvent event ) {
        presenter.reload();
    }
    
    @UiHandler("editButton")
    public void onTapEdit( TapEvent event ) {
        places.goTo(  factory.newRestfulPlace( this.presenter.get(),
                RestfulActionEnum.EDIT ) );
    }

    @UiHandler("saveButton")
    public void onTapSave( TapEvent event ) {
        presenter.save( editorDriver.flush() );
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

    public boolean isDirty() {
        return editorDriver.isDirty();
    }

}