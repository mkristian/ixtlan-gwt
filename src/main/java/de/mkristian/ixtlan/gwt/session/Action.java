package de.mkristian.ixtlan.gwt.session;

import java.util.Set;

import org.fusesource.restygwt.client.Json;
import org.fusesource.restygwt.client.Json.Style;

@Json(style = Style.RAILS)
public class Action {
    static enum CRUD { create, retrieve, update, delete }
    
    public CRUD name;
    //public String verb;
    public Set<String> associations;
}
