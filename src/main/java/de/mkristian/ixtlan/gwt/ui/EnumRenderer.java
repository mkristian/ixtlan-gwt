package de.mkristian.ixtlan.gwt.ui;

import com.google.gwt.text.shared.AbstractRenderer;

class EnumRenderer<T extends Enum<?>> extends AbstractRenderer<T> {

    @Override
    public String render( T object )
    {
        return object.name();
    }
}