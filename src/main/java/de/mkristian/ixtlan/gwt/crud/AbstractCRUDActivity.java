package de.mkristian.ixtlan.gwt.crud;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceController.Delegate;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;

import de.mkristian.ixtlan.gwt.common.DetailActivity;
import de.mkristian.ixtlan.gwt.common.ModelButton;
import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.events.ModelEventHandler;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;
import de.mkristian.ixtlan.gwt.session.SessionFacade;

public abstract class AbstractCRUDActivity<T extends Identifiable> 
		extends DetailActivity {

    protected final RestfulPlace<T, ?> place;
    protected final CRUDPresenter<T> presenter;
    protected final PlaceController places;
    protected final CRUDFactory<T, ?> factory;
    protected final Delegate delegate;

    public AbstractCRUDActivity( RestfulPlace<T, ?> place, 
                                 PlaceController places,
                                 CRUDPresenter<T> presenter,
                                 CRUDFactory<T, ?> factory,
                                 Delegate delegate,
                                 SessionFacade session,
                                 String eventId ) {
    	super( presenter.view(), presenter.listView(), session, eventId );
		presenter.view().getHeaderText().setText( "TO BE CHANGED" );
		presenter.listView().getHeaderText().setText( "TO BE CHANGED List" );
        this.place = place;
        this.presenter = presenter;
        this.places = places;
        this.factory = factory;
        this.delegate = delegate;
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
                        goToIndexPlace( event.getModel() );
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
                doShow();
                break;
            case NEW:
                presenter.showNew( place.model );
                startView();
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
                doShowAll();
                break;
            default:
                presenter.unknownAction( place.action );
                break;
        }
    }

    protected void doShow()
    {
        if ( place.model != null ) {
            presenter.show( place.model );
        }
        else {
            presenter.show( place.id );
        }
    }

    protected void doShowAll()
    {
        presenter.showAll();
    }
    
    protected void goToIndexPlace( T model )
    {
        places.goTo( factory.newRestfulPlace( RestfulActionEnum.INDEX ) );
    }

    protected void goToNewPlace()
    {
        places.goTo( factory.newRestfulPlace( RestfulActionEnum.NEW ) );
    }

    protected void reloadAll()
    {
        presenter.reload();
    }

	protected void startListView() {
		final CRUDListView<T> view = presenter.listView();
        
        addHandlerRegistration( view.getNewButton().addTapHandler( new TapHandler() {
        	
        	@Override
        	public void onTap(TapEvent event) {
                goToNewPlace();
        	}
        } ) );


        addHandlerRegistration( view.getReloadButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
		        reloadAll();
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

    protected void startView() {
		final CRUDView<T> view = presenter.view();
        
        addHandlerRegistration( view.getListAllButton().addTapHandler(new TapHandler() {
        
              @Override
              public void onTap(TapEvent event) {
                  goToIndexPlace( presenter.current() );
              }
        } ) );

        addHandlerRegistration( view.getReloadButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
                String warning = mayStop();
                if ( warning == null || delegate.confirm( warning ) ){
                    presenter.reload();
                }
			}
		} ) );
        
        addHandlerRegistration( view.getNewButton().addTapHandler( new TapHandler() {
        	
        	@Override
        	public void onTap(TapEvent event) {
                goToNewPlace();
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
				if ( presenter.current() == null | presenter.current().isNew() ){
				    goToIndexPlace( presenter.current() );
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
		        presenter.update( view.flush() );
			}
		} ) );
        
        addHandlerRegistration( view.getDeleteButton().addTapHandler( new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
			    String warning = mayStop();
			    if ( warning == null || delegate.confirm( warning ) ){
			        presenter.delete( view.flush() );
			    }
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