package de.mkristian.ixtlan.gwt.readonly;

import java.util.List;

import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;

import de.mkristian.ixtlan.gwt.common.DetailListViewImpl;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;

public abstract class ReadonlyListViewImpl<T extends Identifiable>
	extends DetailListViewImpl<T>
    implements ReadonlyListView<T> {

	public ReadonlyListViewImpl( ReadonlyListWidget<T> list ) {
		super( list );
        ButtonBar footer = new ButtonBar();
        footer.add( reloadButton );    
        main.add( footer );
	}
    
	@Override
    public void reset( List<T> models, RestfulActionEnum permission ) {
    	((ReadonlyListWidget<T>) list).reset( models );
    }
}