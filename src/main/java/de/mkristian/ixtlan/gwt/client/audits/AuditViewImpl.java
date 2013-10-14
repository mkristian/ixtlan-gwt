package de.mkristian.ixtlan.gwt.client.audits;


import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;

import de.mkristian.ixtlan.gwt.readonly.ReadonlyViewImpl;

@Singleton
public class AuditViewImpl 
		extends ReadonlyViewImpl<Audit>
		implements AuditView {

  static interface EditorDriver extends SimpleBeanEditorDriver<Audit, AuditEditor> {}

  @SuppressWarnings("unchecked")
  public AuditViewImpl() {
      super( new AuditEditor(),
    		  (SimpleBeanEditorDriver<Audit, Editor<Audit>>) GWT.create( EditorDriver.class ) );
  }
}
