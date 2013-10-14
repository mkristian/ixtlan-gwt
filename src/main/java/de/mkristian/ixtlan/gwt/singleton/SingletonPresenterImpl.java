package de.mkristian.ixtlan.gwt.singleton;

import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.EDIT;
import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.SHOW;
import de.mkristian.ixtlan.gwt.common.AbstractPresenter;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.session.Guard;
import de.mkristian.ixtlan.gwt.utils.ErrorHandlerWithDisplay;

public abstract class SingletonPresenterImpl<T> 
            extends AbstractPresenter<T>
            implements SingletonPresenter<T> {

    protected final SingletonRemote<T> remote;
    protected final SingletonView<T> view;

    protected final SingletonFactory<T, ?> factory;
    protected final Guard guard;
    protected boolean isEditing = false;
    private T model;
    
    private RestfulActionEnum permission;

    public SingletonPresenterImpl( ErrorHandlerWithDisplay errors,
                SingletonView<T> view,
                SingletonRemote<T> remote,
                SingletonFactory<T, ?> factory,
                Guard guard ) {
        super(errors);
        this.view = view;
        this.remote = remote;
        this.factory = factory;
        this.guard = guard;
    }

    @Override
    public SingletonView<T> view(){
        return view;
    }

    @Override
    public T current() {
        return model;
    }
//    
//	public void update() {
//    	update( view.flush() );
//	}

    @Override
	public void update( final T model ) {
        this.model = model;
        isEditing = false;
        remote.update( model );
    }
//
//    @Override
//    public void show(){
//        isEditing = false;
//        setWidget( view );
//        remote.retrieve();
//    }
//
//    @Override
//    public void edit(){
//        isEditing = true;
//        setWidget( view );
//        remote.retrieve();
//    }

    @Override
    public void reset( T model ) {
        this.model = model;
//        if( isEditing ) {
//            view.edit( permission() );
//        }
//        else {
//            view.show( permission() );
//        }
        view.reset( model );
    }

    @Override
    public void reload() {
        remote.retrieve();
    }

    @Override
    public void show( T model ) {
        remote.retrieve();
        this.model = model;
        isEditing = false;
        setWidget( view );
        view.show( permission() );
        view.reset( model );
    }

    @Override
    public void edit( T model ) {
        this.model = model;
        isEditing = true;
        setWidget( view );
        view.edit( permission() );
        view.reset( model );
    }

    @Override
    public boolean isDirty() {
        return isEditing && view.isDirty();
    }

    private RestfulActionEnum permission(){
        if ( this.permission == null ){
            this.permission = doPermission();
        }
        return this.permission;
    }
    
    private RestfulActionEnum doPermission(){
        if ( guard.isAllowed( factory.placeName(), EDIT ) ){ 
            return EDIT;
        }
        else if ( guard.isAllowed( factory.placeName(), SHOW ) ){ 
            return SHOW;
        }
        else {
            return null;
        }
    }
}