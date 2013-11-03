package de.mkristian.ixtlan.gwt.ui;

import com.google.gwt.text.shared.AbstractRenderer;

import de.mkristian.ixtlan.gwt.models.HasToDisplay;


public class ModelRenderer<T extends HasToDisplay>
          extends AbstractRenderer<T> {

      @Override
      public String render( T object )
      {
          return object == null ? null : object.toDisplay();
      }                                      
  }