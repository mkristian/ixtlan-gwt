package de.mkristian.ixtlan.gwt.crud;

import java.util.List;

import de.mkristian.ixtlan.gwt.common.ListWidget;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;

public abstract class CRUDListWidget<T extends Identifiable>
        extends ListWidget<T> {
	
	public abstract void reset( List<T> models, RestfulActionEnum permission );
	
}