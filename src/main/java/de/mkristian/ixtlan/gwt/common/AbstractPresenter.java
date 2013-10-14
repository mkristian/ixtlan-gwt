package de.mkristian.ixtlan.gwt.common;


import org.fusesource.restygwt.client.Method;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.utils.ErrorHandlerWithDisplay;

public abstract class AbstractPresenter<T> implements Presenter<T> {

    protected final ErrorHandlerWithDisplay errors;
    private AcceptsOneWidget display;

    public AbstractPresenter( ErrorHandlerWithDisplay errors ){
        this.errors = errors;
    }

    public void setDisplay(AcceptsOneWidget display){
        this.display = display;
        this.errors.setDisplay(display);
    }

    protected void setWidget(IsWidget view) {
        display.setWidget(view.asWidget());
    }
    
    public void unknownAction( RestfulAction action ){
        unknownAction( action.name() );
    }

    public void unknownAction( ModelEvent.Action action ){
        unknownAction( action.name() );
    }
    
    protected void unknownAction( String action ){
        errors.show( "unknown action: " + action );
    }
    
    public void onError( Method method, Throwable e ) {
        errors.onError( method, e );
    }
}
