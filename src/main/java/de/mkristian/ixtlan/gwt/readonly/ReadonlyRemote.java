package de.mkristian.ixtlan.gwt.readonly;

import java.util.List;

import org.fusesource.restygwt.client.Method;

import de.mkristian.ixtlan.gwt.common.Remote;
import de.mkristian.ixtlan.gwt.models.Identifiable;

public interface ReadonlyRemote<T extends Identifiable>
        extends Remote<T>{

    void fireRetrieve(Method method, List<T> models);

    void retrieve(int id);
    
    void retrieveAll();
}