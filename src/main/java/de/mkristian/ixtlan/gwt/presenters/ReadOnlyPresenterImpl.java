package de.mkristian.ixtlan.gwt.presenters;

import java.util.List;

import de.mkristian.ixtlan.gwt.caches.RemoteReadOnly;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.utils.ErrorHandlerWithDisplay;
import de.mkristian.ixtlan.gwt.views.ReadOnlyListView;
import de.mkristian.ixtlan.gwt.views.ReadOnlyView;

public class ReadOnlyPresenterImpl<T extends Identifiable> 
            extends AbstractPresenter
            implements ReadOnlyPresenter<T> {

    protected final ReadOnlyView<T> view;
    protected final ReadOnlyListView<T> listView;
    protected final RemoteReadOnly<T> remote;

    public ReadOnlyPresenterImpl( ErrorHandlerWithDisplay errors,
            ReadOnlyView<T> view, 
            ReadOnlyListView<T> listView, 
            RemoteReadOnly<T> remote ) {
        super(errors);
        this.view = view;
        this.listView = listView;
        this.remote = remote;
    }

    public void reset( T model ) {
        view.show( model );
    }

    public void reset( List<T> models ) {
        listView.reset( models );
    }

    public void showAll() {
        setWidget(listView);
        remote.retrieveAll();
    }

    public void show( int id ) {
        setWidget(view);
        remote.retrieve( id );
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