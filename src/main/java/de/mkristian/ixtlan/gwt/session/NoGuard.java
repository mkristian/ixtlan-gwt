package de.mkristian.ixtlan.gwt.session;

import java.util.Set;

import javax.inject.Singleton;

import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;

@Singleton
public class NoGuard implements Guard {

    @Override
    public boolean isAllowed(RestfulPlace<?, ?> place) {
        return true;
    }

    @Override
    public boolean isAllowed(String resourceName, RestfulAction action) {
        return true;
    }

    @Override
    public boolean isAllowed(String resourceName, RestfulAction action,
            String association) {
        return true;
    }

    @Override
    public boolean isAllowed(String resourceName, String action,
            String association) {
        return true;
    }

    @Override
    public boolean isAllowed(String resourceName, String action) {
        return true;
    }

    @Override
    public Set<String> allowedAssociations(String resourceName,
            RestfulAction action) {
        return null;
    }

    @Override
    public Set<String> allowedAssociations(String resourceName, String action) {
        return null;
    }

    @Override
    public Set<String> allowedAssociations(String resourceName) {
        return null;
    }
    
}