package de.mkristian.ixtlan.gwt.views;

import java.util.List;

import de.mkristian.ixtlan.gwt.models.Identifiable;

public abstract class ReadOnlyListWidget<T extends Identifiable> extends ListWidget<T> {
	
	public abstract void reset( List<T> models );

}