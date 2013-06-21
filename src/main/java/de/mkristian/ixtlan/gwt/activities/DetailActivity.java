package de.mkristian.ixtlan.gwt.activities;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.event.ShowMasterEvent;

import de.mkristian.ixtlan.gwt.events.ActionEvent;
import de.mkristian.ixtlan.gwt.events.ActionNames;
import de.mkristian.ixtlan.gwt.session.SessionFacade;
import de.mkristian.ixtlan.gwt.views.DetailListView;
import de.mkristian.ixtlan.gwt.views.DetailView;

public class DetailActivity extends MGWTAbstractActivity {

  private final DetailView detailView;
  private final DetailListView detailListView;
  private final String eventId;
  protected final SessionFacade session;

  public DetailActivity( DetailView detailView,
          SessionFacade session, String eventId ) {
	  this( detailView, null, session, eventId );
  }
  
  public DetailActivity( DetailView detailView,
		  DetailListView detailListView,
          SessionFacade session, String eventId ) {
    this.detailView = detailView;
    this.detailListView = detailListView;
    this.eventId = eventId;
    this.session = session;
  }

  @Override
  public void start(AcceptsOneWidget panel, final EventBus eventBus) {
    addHandlerRegistration(detailView.getMainButton().addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
        eventBus.fireEvent( new ShowMasterEvent( eventId ) );

      }
    }));

    addHandlerRegistration(detailView.getBackbutton().addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
          ActionEvent.fire( eventBus, ActionNames.BACK );
      }
    }));

    addHandlerRegistration(detailView.getLogoutButton().addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
          session.destroy();
      }
    }));

    if( detailListView != null ){
      addHandlerRegistration(detailListView.getLogoutButton().addTapHandler(new TapHandler() {
    		
        @Override
        public void onTap(TapEvent event) {
          session.destroy();
        }
      }));
    }
  }

}
