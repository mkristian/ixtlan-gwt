package de.mkristian.ixtlan.gwt.presenters;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.places.RestfulAction;

public interface Presenter {

    void setDisplay(AcceptsOneWidget display);

    void unknownAction(RestfulAction action);

    void unknownAction(Action action);

    void onError(Method method, Throwable e);

}