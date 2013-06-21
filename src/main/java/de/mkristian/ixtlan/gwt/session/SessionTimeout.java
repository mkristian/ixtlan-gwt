/**
 *
 */
package de.mkristian.ixtlan.gwt.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.events.ModelEvent.Action;
import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.utils.DisplayMessage;

@Singleton
public class SessionTimeout{

    private Session session;

    private int countdown;

    private final EventBus eventBus;

    private final DisplayMessage messages;

    @Inject
    public SessionTimeout( EventBus eventBus, DisplayMessage messages ){
        this.eventBus = eventBus;
        this.messages = messages;
        eventBus.addHandler( SessionEvent.TYPE, new SessionEventHandler() {
            
            @Override
            public void onModelEvent(ModelEvent<Session> event) {
                switch( event.getAction() ){
                    case CREATE:
                        boolean notRunning = session == null; 
                        session = event.getModel();
                        countdown = session.idleSessionTimeout;
                        if( notRunning ){
                            runTimer();
                        }
                        break;
                    case DESTROY:
                        session = null;
                        countdown = -1;
                        break;
                    default:
                }
            }
        });
    }

    public void timeout(){
        Session oldSession = session;
        session = null;
        countdown = -1;
        eventBus.fireEvent( new SessionEvent( null, oldSession, Action.DESTROY ) );
        messages.info( "timeout" );
    }

    private void runTimer() {
        Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {
            
            public boolean execute() {
                GWT.log("idle timeout: " + countdown + " minutes left");
                if(countdown == 0){
                    timeout();
                }
                countdown--;
                return countdown > -1;
            }
        }, 60000);
    }
    
    public void resetCountDown(){
        countdown = this.session == null ? -1 : this.session.idleSessionTimeout;
    }
}
