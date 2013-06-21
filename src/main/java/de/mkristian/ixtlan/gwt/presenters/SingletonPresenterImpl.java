package de.mkristian.ixtlan.gwt.presenters;

import de.mkristian.ixtlan.gwt.caches.RemoteSingleton;
import de.mkristian.ixtlan.gwt.utils.ErrorHandlerWithDisplay;
import de.mkristian.ixtlan.gwt.views.SingletonView;

public abstract class SingletonPresenterImpl<T> 
            extends AbstractPresenter
            implements SingletonPresenter<T> {

    protected final RemoteSingleton<T> remote;
    protected final SingletonView<T> view;
    
    protected boolean isEditing = false;
    private T model;

    public SingletonPresenterImpl( ErrorHandlerWithDisplay errors,
                SingletonView<T> view,
                RemoteSingleton<T> remote) {
        super(errors);
        this.view = view;
        this.remote = remote;
    }

    @Override
    public SingletonView<T> view(){
        return view;
    }

    @Override
    public T current() {
        return model;
    }
    
    @Override
	public void save() {
    	save( view.flush() );
	}

	private void save( final T model ) {
        this.model = model;
        isEditing = false;
        remote.update( model );
    }

    @Override
    public void show(){
        isEditing = false;
        setWidget( view );
        remote.retrieve();
    }

    @Override
    public void edit(){
        isEditing = true;
        setWidget( view );
        remote.retrieve();
    }

    @Override
    public void reset( T model ) {
        this.model = model;
        if( isEditing ) {
            view.edit( model );
        }
        else {
            view.show( model );
        }
    }

    @Override
    public void reload() {
        if( isEditing ) {
            edit();
        }
        else {
            show();
        }
    }

    @Override
    public void show( T model ) {
        this.model = model;
        isEditing = false;
        setWidget( view );
        view.show( model );
    }

    @Override
    public void edit( T model ) {
        this.model = model;
        isEditing = true;
        setWidget( view );
        view.edit( model );
    }

    @Override
    public boolean isDirty() {
        return isEditing && view.isDirty();
    }

}