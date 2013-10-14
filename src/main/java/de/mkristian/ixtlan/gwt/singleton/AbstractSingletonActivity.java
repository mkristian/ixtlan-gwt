package de.mkristian.ixtlan.gwt.singleton;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;

import de.mkristian.ixtlan.gwt.common.DetailActivity;
import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEventHandler;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.session.SessionFacade;

public abstract class AbstractSingletonActivity<T> extends DetailActivity {

    protected final RestfulPlace<T, ?> place;
    protected final SingletonPresenter<T> presenter;
    protected final PlaceController places;
    protected final SingletonFactory<T, ?> factory;

    public AbstractSingletonActivity( RestfulPlace<T, ?> place,
            SingletonPresenter<T> presenter,
            PlaceController places,
            SingletonFactory<T, ?> factory,
            SessionFacade session,
            String eventId ) {
        super( presenter.view(), session, eventId );
        this.place = place;
        this.presenter = presenter;
        this.places = places;
        this.factory = factory;
    }
    
    @Override 
    public void start( AcceptsOneWidget display, EventBus eventBus ) {
        super.start( display, eventBus );
        presenter.setDisplay( display );
        
        final SingletonView<T> view = presenter.view();
		addHandlerRegistration( view.getCancelButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
		        places.goTo( factory.newRestfulPlace( presenter.current(),
		                RestfulActionEnum.SHOW ) );
			}
		} ) );

        addHandlerRegistration( view.getReloadButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
		        presenter.reload();
			}
		} ) );

        addHandlerRegistration( view.getEditButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
		        places.goTo( factory.newRestfulPlace( presenter.current(),
		                                              RestfulActionEnum.EDIT ) );
			}
		} ) );

        addHandlerRegistration( view.getSaveButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
		        presenter.update( view.flush() );
			}
		} ) );
        
        eventBus.addHandler( factory.eventType(), new ModelEventHandler<T>(){
            @Override
            public void onModelEvent(ModelEvent<T> event) {
                switch( event.getAction() ){
                    case LOAD:
                        if ( event.getModel() != null ) {
                            presenter.reset( event.getModel() );
                        }
                        break;
                    case UPDATE:
                        places.goTo( factory.newRestfulPlace( event.getModel(), RestfulActionEnum.SHOW ) );
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
            case EDIT:
                if ( place.model != null ){
                    presenter.edit( place.model );
                }
                else {
                    presenter.edit( factory.newModel() );
                }
                break;
            case SHOW:
                if ( place.model != null ){
                    presenter.show( place.model );
                }
                else {
                    presenter.show( factory.newModel() );
                }
                break;
            default:
                presenter.unknownAction( place.action );
                break;
        }
    }

    @Override
    public String mayStop() {
        if ( presenter.isDirty() ){
            return "there is unsaved data.";
        }
        else {
            return null;
        }
    }

}