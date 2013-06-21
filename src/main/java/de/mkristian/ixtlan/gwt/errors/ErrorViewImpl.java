package de.mkristian.ixtlan.gwt.errors;


import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;

import de.mkristian.ixtlan.gwt.views.ReadOnlyViewImpl;

@Singleton
public class ErrorViewImpl 
		extends ReadOnlyViewImpl<Error>
		implements ErrorView {

  static interface EditorDriver extends SimpleBeanEditorDriver<Error, ErrorEditor> {}

  @SuppressWarnings("unchecked")
  public ErrorViewImpl() {
      super( new ErrorEditor(),
    		  (SimpleBeanEditorDriver<Error, Editor<Error>>) GWT.create( EditorDriver.class ) );
  }
}
