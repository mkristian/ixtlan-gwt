package de.mkristian.ixtlan.gwt.ui;

import java.util.Collection;

import com.google.gwt.text.shared.Renderer;
import com.google.gwt.view.client.ProvidesKey;

public class EnumValueListBox<T extends Enum<?>>
        extends ValueListBox<T> {
    
    public EnumValueListBox( T[] values )
    {
        this( new EnumRenderer<T>(), new EnumKeyProvider<T>(), values );
    }
    
    public EnumValueListBox( Renderer<T> renderer,
                             ProvidesKey<T> keys,
                             T[] values  )
    {
        super( renderer, keys );
        assert values != null;
        for( T value : values )
        {
                addValue( value );
        }
    }

    @Override
    public void setAcceptableValues( Collection<T> newValues )
    {
        throw new RuntimeException( "not implemented for enums" );
    }
}