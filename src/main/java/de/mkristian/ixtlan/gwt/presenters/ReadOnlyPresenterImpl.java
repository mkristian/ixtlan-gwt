package de.mkristian.ixtlan.gwt.presenters;

import java.util.List;

import com.google.gwt.core.shared.GWT;

import de.mkristian.ixtlan.gwt.caches.Cache;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.utils.ErrorHandlerWithDisplay;
import de.mkristian.ixtlan.gwt.views.ReadOnlyListView;
import de.mkristian.ixtlan.gwt.views.ReadOnlyView;

public class ReadOnlyPresenterImpl<T extends Identifiable> 
            extends AbstractPresenter
            implements ReadOnlyPresenter<T> {

	private final ReadOnlyView<T> view;
    private final ReadOnlyListView<T> listView;
    private final Cache<T> cache;
	private Integer id;

    public ReadOnlyPresenterImpl( ErrorHandlerWithDisplay errors,
            ReadOnlyView<T> view, 
            ReadOnlyListView<T> listView, 
            Cache<T> cache ) {
        super(errors);
        this.view = view;
        this.listView = listView;
        this.cache = cache;
    }

    public void reset( T model ) {
        view.show( model );
    }

    public void reset( List<T> models ) {
        listView.reset( models );
    }

    public void showAll() {
    	id = null;
        setWidget( listView );
        reset( cache.getOrLoadModels() );
    }

    public void show( int id ) {
    	this.id = id;
        setWidget(view);
        reset( cache.getOrLoadModel( id ) );
    }

    @Override
    public void reload() {
    	GWT.log( "readonly presenter " + id );
    	if ( id == null ){
            reset( cache.getOrLoadModels() );
    	}
    	else {
            reset( cache.getOrLoadModel( id ) );
    	}
    }
    
	@Override
	public ReadOnlyView<T> view() {
		return this.view;
	}

	@Override
	public ReadOnlyListView<T> listView() {
		return this.listView;
	}
}