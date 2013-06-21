package de.mkristian.ixtlan.gwt.utils;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.AcceptsOneWidget;


public class ErrorHandlerWithDisplay extends ErrorHandler{
    private AcceptsOneWidget display;

    @Inject
    public ErrorHandlerWithDisplay( DisplayMessage displayMessage ) {
        super( displayMessage );
    }
    
    public AcceptsOneWidget getDisplay(){
        return this.display;
    }
    
    public void setDisplay( AcceptsOneWidget display ){
        this.display = display;
    }
    
}