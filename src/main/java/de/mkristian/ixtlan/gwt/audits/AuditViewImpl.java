package de.mkristian.ixtlan.gwt.audits;


import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;

import de.mkristian.ixtlan.gwt.views.ReadOnlyViewImpl;

@Singleton
public class AuditViewImpl extends ReadOnlyViewImpl<Audit>
            implements AuditView {

  static interface EditorDriver extends SimpleBeanEditorDriver<Audit, AuditEditor> {}

  @SuppressWarnings("unchecked")
  public AuditViewImpl() {
      super( new AuditEditor(),
    		  (SimpleBeanEditorDriver<Audit, Editor<Audit>>) GWT.create( EditorDriver.class ) );
  }
}
