package de.mkristian.ixtlan.gwt.ui;

import java.util.Collection;

import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ProvidesKey;

public class EnumValueCheckBoxes<T extends Enum<?>>
        extends ValueCheckBoxes<T> {
    
    public <S extends Widget & HasWidgets> EnumValueCheckBoxes( S container, T[] values )
    {
        this( new EnumRenderer<T>(), new EnumKeyProvider<T>(), container, values );
    }
    
    public <S extends Widget & HasWidgets> EnumValueCheckBoxes( Renderer<T> renderer,
                                                                ProvidesKey<T> keys,
                                                                S container,
                                                                T[] values  )
    {
        super( renderer, keys, container );
        if( container != null && values != null )
        {
            for( T value : values )
            {
                addValue( value );
            }
        }
    }

    @Override
    public void setAcceptableValues( Collection<T> newValues )
    {
        throw new RuntimeException( "not implemented for enums" );
    }
    
}