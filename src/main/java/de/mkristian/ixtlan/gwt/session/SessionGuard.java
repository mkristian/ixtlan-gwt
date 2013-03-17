/**
 *
 */
package de.mkristian.ixtlan.gwt.session;

import java.util.Collections;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.events.ModelEvent;
import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;

@Singleton
public class SessionGuard implements Guard {

    private final Set<String> EMPTY_SET = Collections.emptySet();
    
    private Session session;

    @Inject
    public SessionGuard( EventBus eventBus ){
        eventBus.addHandler( SessionEvent.TYPE, new SessionEventHandler() {
            
            @Override
            public void onModelEvent(ModelEvent<Session> event) {
                switch( event.getAction() ){
                    case CREATE:
                        session = event.getModel();
                        break;
                    case DESTROY:
                        session = null;
                        break;
                    default:
                }
            }
        });
    }

    public boolean isAllowed(RestfulPlace<?, ?> place) {
        String name = place == null ? null : place.action.name().toLowerCase();
        String resourceName = place == null ? null : place.resourceName;
        return this.session == null ? false : this.session.isAllowed( resourceName, name, null);
    }

    public boolean isAllowed(String resourceName, RestfulAction action) {
        String name = action == null ? null : action.name().toLowerCase();
        return this.session == null ? false : this.session.isAllowed(resourceName, name, null);
    }

    public boolean isAllowed(String resourceName, RestfulAction action, String association) {
        String name = action == null ? null : action.name().toLowerCase();
        return this.session == null ? false : this.session.isAllowed(resourceName, name, association);
    }

    public boolean isAllowed(String resourceName, String action, String association) {
        return this.session == null ? false : this.session.isAllowed(resourceName, action, association);
    }

    public boolean isAllowed(String resourceName, String action) {
        return this.session == null ? false : this.session.isAllowed(resourceName, action, null);
    }

    public Set<String> allowedAssociations(String resourceName, RestfulAction action) {
        String name = action == null ? null : action.name().toLowerCase();
        return this.session == null ? EMPTY_SET : this.session.allowedAssocations(resourceName, name);
    }

    public Set<String> allowedAssociations(String resourceName, String action) {
        return this.session == null ? EMPTY_SET : this.session.allowedAssocations(resourceName, action);
    }

    public Set<String> allowedAssociations(String resourceName) {
        return this.session == null ? EMPTY_SET : this.session.allowedAssocations(resourceName);
    }
}
