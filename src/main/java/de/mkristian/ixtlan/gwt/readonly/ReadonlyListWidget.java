package de.mkristian.ixtlan.gwt.readonly;

import java.util.List;

import de.mkristian.ixtlan.gwt.common.ListWidget;
import de.mkristian.ixtlan.gwt.models.Identifiable;

public abstract class ReadonlyListWidget<T extends Identifiable>
        extends ListWidget<T> {
	
	public abstract void reset( List<T> models );

}