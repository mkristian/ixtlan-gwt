package de.mkristian.ixtlan.gwt.views;

import java.util.List;

import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;

import de.mkristian.ixtlan.gwt.models.Identifiable;

public abstract class ReadOnlyListViewImpl<T extends Identifiable>
	extends DetailListViewImpl<T> {

	public ReadOnlyListViewImpl( ReadOnlyListWidget<T> list ) {
		super( list );
        ButtonBar footer = new ButtonBar();
        footer.add( reloadButton );    
        main.add( footer );
	}
    
    public void reset( List<T> models ) {
    	((ReadOnlyListWidget<T>) list).reset( models );
    }
}