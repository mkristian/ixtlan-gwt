package de.mkristian.ixtlan.gwt.crud;

import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.DESTROY;
import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.EDIT;
import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.NEW;
import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.SHOW;

import java.util.List;

import de.mkristian.ixtlan.gwt.caches.Cache;
import de.mkristian.ixtlan.gwt.common.AbstractPresenter;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.session.Guard;
import de.mkristian.ixtlan.gwt.utils.ErrorHandlerWithDisplay;

public class CRUDPresenterImpl<T extends Identifiable>
        extends AbstractPresenter<T>
        implements CRUDPresenter<T> {

    protected final CRUDView<T> view;
    protected final CRUDListView<T> listView;
    protected final CRUDRemote<T> remote;
    protected final Cache<T> cache;

    private T model;
    
    protected final CRUDFactory<T, ?> factory;
    protected final Guard guard;
    protected boolean isEditing = false;
    
    private RestfulActionEnum permission;

    public CRUDPresenterImpl( ErrorHandlerWithDisplay errors,
                CRUDView<T> view,
                CRUDListView<T> listView,
                Cache<T> cache,
                CRUDRemote<T> remote,
                CRUDFactory<T, ?> factory,
                Guard guard ) {
        super( errors );
        this.view = view;
        this.listView = listView;
        this.cache = cache;
        this.remote = remote;
        this.factory = factory;
        this.guard = guard;
    }

    @Override
    public CRUDView<T> view() {
        return view;
    }

    @Override
    public CRUDListView<T> listView() {
        return listView;
    }

    public T current() {
        return model;
    }

    @Override
    public void showAll() {
        isEditing = false;
        setWidget( listView );
        reset( cache.getOrLoadModels() );
    }

    @Override
    public void edit( int id ) {
        doEdit( cache.getOrLoadModel( id ) );
    }

    private void doEdit( T model ) {
        this.model = model;
        isEditing = true;
        setWidget( view );
        if( model == null || model.isNew() ){
            view.create( permission() );            
        }
        else{
            view.edit( permission() );
        }
        view.reset( model );
    }

    protected T retrieveModel( T model ){
        T m = cache.getOrLoadModel( model.getId() );
        return m == null ? model : m;
    }
    
    @Override
    public void edit( T model ) {
        doEdit( retrieveModel( model ) );
    }

    @Override
    public void showNew( T model ) {
        doEdit( model == null ? factory.newModel() : model );
    }

    @Override
    public void show(T model) {
        doShow( retrieveModel( model ) );
    }

    private void doShow(T model) {
        this.model = model;
        isEditing = false;
        setWidget( view );
        view.show( permission() );
        view.reset( model );
    }

    @Override
    public void show(int id) {
        doShow( cache.getOrLoadModel( id ) );
    }

    public void create(final T model) {
        this.model = model;
        isEditing = false;
        remote.create( model );
    }

    public void delete(final T model) {
        this.model = model;
        isEditing = false;
        remote.delete( model );
    }

    @Override
    public void reset(T model) {
        this.model = model;
        view.reset( model );
    }

    @Override
    public void reset(List<T> models) {
    	this.model = null;
        listView.reset( models, permission() );
    }

    private RestfulActionEnum permission(){
    	if ( this.permission == null ){
    		this.permission = doPermission();
    	}
    	return this.permission;
    }
    
    private RestfulActionEnum doPermission(){
    	if ( guard.isAllowed( factory.placeName(), DESTROY ) ){ 
        	return DESTROY;
        }
        else if ( guard.isAllowed( factory.placeName(), NEW ) ){ 
        	return NEW;
        }
        else if ( guard.isAllowed( factory.placeName(), EDIT ) ){ 
        	return EDIT;
        }
        else if ( guard.isAllowed( factory.placeName(), SHOW ) ){ 
        	return SHOW;
        }
        else {
        	return null;
        }
    }
    
    @Override
    public void update(T model) { 
        this.model = model;
        isEditing = false;
        remote.update( model );
    }

    @Override
    public boolean isDirty() {
        return isEditing && view.isDirty();
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