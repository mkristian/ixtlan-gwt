package de.mkristian.ixtlan.gwt.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEventHandler;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.places.SingletonFactory;
import de.mkristian.ixtlan.gwt.presenters.SingletonPresenter;
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
        
    public void start( AcceptsOneWidget display, EventBus eventBus ) {
        super.start( display, eventBus );
        presenter.setDisplay( display );
        eventBus.addHandler( factory.eventType(), new ModelEventHandler<T>(){
            //@Override
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
                    presenter.edit();
                }
                break;
            case SHOW:
                if ( place.model != null ){
                    presenter.show( place.model );
                }
                else {
                    presenter.show();
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