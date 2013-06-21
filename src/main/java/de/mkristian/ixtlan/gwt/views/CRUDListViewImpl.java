package de.mkristian.ixtlan.gwt.views;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;
import de.mkristian.ixtlan.gwt.presenters.CRUDPresenter;
import de.mkristian.ixtlan.gwt.session.Guard;

public abstract class CRUDListViewImpl<T extends Identifiable> 
	extends DetailListViewImpl<T> {

    @UiField(provided = true) public Label header;
    @UiField public Button newButton;
    @UiField public FlexTable list;

    private CRUDPresenter<T> presenter;
    
    //private final ClickHandler clickHandler = newClickHandler();
    
    protected final PlaceController places;
    protected final Guard guard;

    public CRUDListViewImpl( String title, Guard guard, PlaceController places ) {
        this.header = new Label( title );
        this.places = places;
        this.guard = guard;
    }

    protected boolean isAllowed( RestfulActionEnum action ){
        return guard.isAllowed( placeName(), action );
    }
    
    protected abstract String placeName();

    protected abstract Place newPlace();
    
    protected abstract Place place( T model, RestfulAction action );

    @UiHandler("newButton")
    public void onClickNew(ClickEvent e) {
        places.goTo( newPlace() );
    }
//    
//    protected Button newButton(RestfulActionEnum action, T model) {
//        ModelButton<T> button = new ModelButton<T>(action, model);
//        button.addClickHandler(clickHandler);
//        return button;
//    }
//
//    protected ClickHandler newClickHandler(){
//        return new ClickHandler() {
//        
//            @SuppressWarnings("unchecked")
//            public void onClick(ClickEvent event) {
//                ModelButton<T> button = (ModelButton<T>)event.getSource();
//                switch(button.action){
//                    case DESTROY:
//                        presenter.delete(button.model);
//                        break;
//                    case EDIT:
//                    case SHOW:
//                        places.goTo( place( button.model, button.action ) );
//                        break;
//                    default:
//                        presenter.unknownAction( button.action );
//                }
//            }
//        };
//    }
    
    public void remove(T model) {
        String id = model.getId() + "";
        for(int i = 0; i < list.getRowCount(); i++){
            if(list.getText(i, 0).equals(id)){
                list.removeRow(i);
                return;
            }
        }
    }

    public void setPresenter(CRUDPresenter<T> presenter) {
        this.presenter = presenter;
    }

}