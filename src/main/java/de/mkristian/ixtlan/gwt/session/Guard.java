package de.mkristian.ixtlan.gwt.session;

import java.util.Set;

import com.google.inject.ImplementedBy;

import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;

@ImplementedBy( SessionGuard.class )
public interface Guard {

    boolean isAllowed(RestfulPlace<?, ?> place);

    boolean isAllowed(String resourceName, RestfulAction action);

    boolean isAllowed(String resourceName, RestfulAction action,
            String association);

    boolean isAllowed(String resourceName, String action, String association);

    boolean isAllowed(String resourceName, String action);

    Set<String> allowedAssociations(String resourceName, RestfulAction action);

    Set<String> allowedAssociations(String resourceName, String action);

    Set<String> allowedAssociations(String resourceName);
    
}