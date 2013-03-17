package de.mkristian.ixtlan.gwt.models;

import java.util.List;

public interface Query<T> {
   
    String toQuery();
    
    List<T> filter(List<T> models);
}
