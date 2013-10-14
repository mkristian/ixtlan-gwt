package de.mkristian.ixtlan.gwt.readonly;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;

import de.mkristian.ixtlan.gwt.common.DetailViewImpl;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.ui.EnabledEditor;

public abstract class ReadonlyViewImpl<T extends Identifiable> 
	extends DetailViewImpl {
    
    protected final EnabledEditor<T> editor;

    protected final SimpleBeanEditorDriver<T, Editor<T>> editorDriver;

    public ReadonlyViewImpl( final EnabledEditor<T> editor,
            SimpleBeanEditorDriver<T, Editor<T>> driver ) {
        this.editor = editor;
        this.editorDriver = driver;
        this.editorDriver.initialize( editor );
		listAllButton.setVisible( true );
		
        ButtonBar footer = new ButtonBar();     
        footer.add( reloadButton );
        
        setWidget( editor );
        main.add( footer );;
    }

    public void reset( T model ) {
        editorDriver.edit( model );
        editor.setEnabled( false );
    }

}