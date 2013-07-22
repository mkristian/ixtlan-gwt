package de.mkristian.ixtlan.gwt.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEventHandler;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.Factory;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.presenters.ReadOnlyPresenter;
import de.mkristian.ixtlan.gwt.session.SessionFacade;
import de.mkristian.ixtlan.gwt.views.ModelButton;
import de.mkristian.ixtlan.gwt.views.ReadOnlyListView;
import de.mkristian.ixtlan.gwt.views.ReadOnlyView;

public abstract class AbstractReadOnlyActivity<T extends Identifiable> 
            extends DetailActivity {

    protected final RestfulPlace<T, ?> place;
    protected final ReadOnlyPresenter<T> presenter;
	protected final Factory<T, ?> factory;
	protected final PlaceController places;

    public AbstractReadOnlyActivity( RestfulPlace<T, ?> place,
            ReadOnlyPresenter<T> presenter,
            PlaceController places,
            Factory<T, ?> factory,
            SessionFacade session,
            String eventId ) {  
        super( presenter.view(), presenter.listView(), session, eventId); 
        this.place = place;
        this.presenter = presenter;
        this.places = places;
        this.factory = factory;
    }

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
    	super.start( display, eventBus );
        presenter.setDisplay( display );
        
        eventBus.addHandler( factory.eventType(), new ModelEventHandler<T>(){
        	
            @Override
            public void onModelEvent(ModelEvent<T> event) {
                switch(event.getAction()){
                    case LOAD:
                        if (event.getModel() != null) {
                        	presenter.reset( event.getModel() );
                        }
                        if (event.getModels() != null) {
                            presenter.reset( event.getModels() );
                        }
                        break;
                    case ERROR:
                        presenter.onError( event.getMethod(), event.getThrowable());
                        break;
                    default:
                        presenter.unknownAction( event.getAction() );
                        break;
                }
            }
        });
        switch( RestfulActionEnum.valueOf( place.action ) ){
            case SHOW:
            	startView();
                presenter.show( place.id );
                break;
            case INDEX:
            	startListView();
                presenter.showAll();
                break;
            default:
                presenter.unknownAction( place.action );
                break;
        }
    }

	private void startListView() {
		ReadOnlyListView<T> view = presenter.listView();
		
        addHandlerRegistration( view.getReloadButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
		        presenter.reload();
			}
		} ) );
        
		addHandlerRegistration( view.setTapHandler( new TapHandler() {
			
			@Override
			public void onTap( TapEvent event ) {
                @SuppressWarnings("unchecked")
				ModelButton<T> button = (ModelButton<T>)event.getSource();
                switch(button.action){
                    case SHOW:
                        places.goTo( factory.newRestfulPlace( button.model, button.action ) );
                        break;
                    default:
                    	presenter.unknownAction( button.action );
                }
			}
		} ) );
	}

	private void startView() {
		ReadOnlyView<T> view = presenter.view();
		
        addHandlerRegistration( view.getReloadButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
		        presenter.reload();
			}
		} ) );
        addHandlerRegistration( view.getListAllButton().addTapHandler(new TapHandler() {

    	      @Override
    	      public void onTap(TapEvent event) {
    	    	  places.goTo( factory.newRestfulPlace( RestfulActionEnum.INDEX ) );				
    	      }
    	} ) );
	}

}