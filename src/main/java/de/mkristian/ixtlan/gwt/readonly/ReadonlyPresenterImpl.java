package de.mkristian.ixtlan.gwt.readonly;

import java.util.List;

import de.mkristian.ixtlan.gwt.caches.Cache;
import de.mkristian.ixtlan.gwt.common.AbstractPresenter;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.utils.ErrorHandlerWithDisplay;

public class ReadonlyPresenterImpl<T extends Identifiable> 
            extends AbstractPresenter<T>
            implements ReadonlyPresenter<T> {

	protected final ReadonlyView<T> view;
    protected final ReadonlyListView<T> listView;
    protected final ReadonlyRemote<T> remote;
    protected final Cache<T> cache;
    private T model;
	
    public ReadonlyPresenterImpl( ErrorHandlerWithDisplay errors,
            ReadonlyView<T> view, 
            ReadonlyListView<T> listView, 
            Cache<T> cache,
            ReadonlyRemote<T> remote ) {
        super( errors );
        this.view = view;
        this.listView = listView;
        this.cache = cache;
        this.remote = remote;
    }

    @Override
    public ReadonlyView<T> view() {
        return view;
    }

    @Override
    public ReadonlyListView<T> listView() {
        return listView;
    }
    
    public T current() {
        return model;
    }

    public void showAll() {
        setWidget( listView );
        reset( cache.getOrLoadModels() );
    }

    protected T retrieveModel( T model ){
        T m = cache.getOrLoadModel( model.getId() );
        return m == null ? model : m;
    }
    
    @Override
    public void show(T model) {
        doShow( retrieveModel( model ) );
    }

    private void doShow(T model) {
        this.model = model;
        setWidget( view );
        view.reset( model );
    }

    @Override
    public void show(int id) {
        doShow( cache.getOrLoadModel( id ) );
    }
//    public void show( int id ) {
//    	this.id = id;
//        setWidget(view);
//        reset( cache.getOrLoadModel( id ) );
//    }

    public void reset( T model ) {
        this.model = model;
        view.reset( model );
    }

    public void reset( List<T> models ) {
        this.model = null;
        // hardcode permission which allows to reuse interface
        listView.reset( models, RestfulActionEnum.SHOW );
    }

    @Override
    public void reload() {
    	if ( model == null ){
            remote.retrieveAll();
        }
        else {
            remote.retrieve( model.getId() );
    	}
    }
 }