package de.mkristian.ixtlan.gwt.presenters;

import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.NEW;
import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.DESTROY;
import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.EDIT;
import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.SHOW;

import java.util.List;

import com.google.gwt.core.shared.GWT;

import de.mkristian.ixtlan.gwt.caches.Cache;
import de.mkristian.ixtlan.gwt.caches.Remote;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.Factory;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.session.Guard;
import de.mkristian.ixtlan.gwt.utils.ErrorHandlerWithDisplay;
import de.mkristian.ixtlan.gwt.views.CRUDListView;
import de.mkristian.ixtlan.gwt.views.CRUDView;

public class CRUDPresenterImpl<T extends Identifiable>
        extends AbstractPresenter
        implements CRUDPresenter<T> {

    private final CRUDView<T> view;
    private final CRUDListView<T> listView;
    private final Cache<T> cache;
    private final Remote<T> remote;
	private final Factory<T, ?> factory;
	private final Guard guard;
    private boolean isEditing = false;
    private T model;
	private RestfulActionEnum permission;

    public CRUDPresenterImpl( ErrorHandlerWithDisplay errors,
                CRUDView<T> view,
                CRUDListView<T> listView,
                Cache<T> cache,
                Remote<T> remote,
                Factory<T, ?> factory,
                Guard guard ) {
        super( errors );
        this.view = view;
        this.listView = listView;
        this.cache = cache;
        this.remote = remote;
        this.factory = factory;
        this.guard = guard;
    }

    public CRUDView<T> view() {
        return view;
    }

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
        view.edit( permission() );
        view.reset( model );
    }

    @Override
    public void edit( T model ) {
        T m = cache.getOrLoadModel( model.getId() );
        doEdit( m == null ? model : m );
    }
    
    @Override
    public void showNew() {
        isEditing = true;
        setWidget( view );
        view.create( permission() );
        view.reset( factory.newModel() );
    }

    @Override
    public void show(T model) {
        T m = cache.getOrLoadModel( model.getId() );
        doShow( m == null ? model : m );
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
        else if ( guard.isAllowed( factory.placeName(), DESTROY ) ){ 
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
    public void save(T model) { 
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
    	GWT.log( "crudpresenter " + model);
    	if ( model == null ){
    		remote.retrieveAll();
    	}
    	else {
    		remote.retrieve( model.getId() );
    	}
    }
}