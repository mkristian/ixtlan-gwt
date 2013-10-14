package de.mkristian.ixtlan.gwt.readonly;

import java.util.List;

import de.mkristian.ixtlan.gwt.common.DetailListView;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;

public interface ReadonlyListView<T>
        extends DetailListView {
	
    void reset( List<T> models, RestfulActionEnum permission );
    
}