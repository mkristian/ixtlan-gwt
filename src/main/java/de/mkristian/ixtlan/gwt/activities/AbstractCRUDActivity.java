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
import de.mkristian.ixtlan.gwt.presenters.CRUDPresenter;
import de.mkristian.ixtlan.gwt.session.SessionFacade;
import de.mkristian.ixtlan.gwt.views.CRUDListView;
import de.mkristian.ixtlan.gwt.views.CRUDView;
import de.mkristian.ixtlan.gwt.views.ModelButton;

public abstract class AbstractCRUDActivity<T extends Identifiable> 
		extends DetailActivity {

    protected final RestfulPlace<T, ?> place;
    protected final CRUDPresenter<T> presenter;
    protected final PlaceController places;
    protected final Factory<T, ?> factory;

    public AbstractCRUDActivity( RestfulPlace<T, ?> place, 
            CRUDPresenter<T> presenter,
            PlaceController places,
            Factory<T, ?> factory,
            SessionFacade session,
            String eventId ) {
    	super( presenter.view(), presenter.listView(), session, eventId );
		presenter.view().getHeaderText().setText( "TO BE CHANGED" );
		presenter.listView().getHeaderText().setText( "TO BE CHANGED List" );
        this.place = place;
        this.presenter = presenter;
        this.places = places;
        this.factory = factory;
    }

    @Override
    public void start( AcceptsOneWidget display, EventBus eventBus ) {
    	super.start( display, eventBus );
        presenter.setDisplay( display );

        eventBus.addHandler( factory.eventType(), new ModelEventHandler<T>(){
        	@Override
            public void onModelEvent( ModelEvent<T> event ) {
                switch(event.getAction()){
                    case LOAD:
                        if (event.getModel() != null) {
                            presenter.reset( event.getModel() );
                        }
                        if (event.getModels() != null) {
                            presenter.reset( event.getModels() );
                        }
                        break;
                    case UPDATE:
                    case CREATE:
                        places.goTo( factory.newRestfulPlace( event.getModel(),
                        		RestfulActionEnum.SHOW ) );
                        break;
                    case DESTROY:
                        places.goTo( factory.newRestfulPlace( RestfulActionEnum.INDEX ) );
                        presenter.listView().remove( event.getModel() );
                        break;
                    case ERROR:
                        presenter.onError( event.getMethod(), 
                                    event.getThrowable() );
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
                if ( place.model != null ) {
                    presenter.show( place.model );
                }
                else {
                    presenter.show( place.id );
                }
                break;
            case NEW:
                startView();
                presenter.showNew();
                break;
            case EDIT:
                startView();
                if ( place.model != null ) {
                    presenter.edit( place.model );
                }
                else {
                    presenter.edit( place.id );
                }
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
		final CRUDListView<T> view = presenter.listView();
        
        addHandlerRegistration( view.getNewButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
		        places.goTo( factory.newRestfulPlace( RestfulActionEnum.NEW ) );
			}
		} ) );


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
                	case DESTROY:
                		presenter.delete( button.model );
                		break;
                	case EDIT:
                    case SHOW:
                        places.goTo( factory.newRestfulPlace( button.model,
                        		button.action ) );
                        break;
                    default:
                    	presenter.unknownAction( button.action );
                }
			}
		} ) );
	}

	private void startView() {
		final CRUDView<T> view = presenter.view();
        
        addHandlerRegistration( view.getListAllButton().addTapHandler(new TapHandler() {

    	      @Override
    	      public void onTap(TapEvent event) {
    	    	  places.goTo( factory.newRestfulPlace( RestfulActionEnum.INDEX ) );				
    	      }
    	} ) );

        addHandlerRegistration( view.getReloadButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
		        presenter.reload();
			}
		} ) );
        
		addHandlerRegistration( view.getNewButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
		        places.goTo( factory.newRestfulPlace( presenter.current(),
		                RestfulActionEnum.NEW ) );
			}
		} ) );

        addHandlerRegistration( view.getCreateButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
		        presenter.create( view.flush() );
			}
		} ) );

		addHandlerRegistration( view.getCancelButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				if ( presenter.current() == null ){
					places.goTo( factory.newRestfulPlace( RestfulActionEnum.INDEX ) );
					
				}
				else {
					places.goTo( factory.newRestfulPlace( presenter.current(),
		                RestfulActionEnum.SHOW ) );
				}
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
		        presenter.save( view.flush() );
			}
		} ) );
        
        addHandlerRegistration( view.getDeleteButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
		        presenter.delete( view.flush() );
			}
		} ) );
	}

    @Override
    public String mayStop() {
        if (presenter.isDirty()){
            return "there is unsaved data.";
        }
        else {
            return null;
        }
    }
}