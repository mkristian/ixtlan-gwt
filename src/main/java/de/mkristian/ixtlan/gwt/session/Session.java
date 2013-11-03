package de.mkristian.ixtlan.gwt.session;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.fusesource.restygwt.client.Json;
import org.fusesource.restygwt.client.Json.Style;

import com.google.gwt.core.client.GWT;

@Json(style = Style.RAILS)
public class Session {

    @Json(name = "idle_session_timeout")
    public final int idleSessionTimeout;

    public final User user;

    public final Set<Permission> permissions;

    transient private Map<String, Set<String>> allow;
    transient private Map<String, Set<String>> deny;

    transient private HashMap<String, Set<String>> associations;
    
    @JsonCreator
    public Session(@JsonProperty("user") User user, 
            @JsonProperty("idleSessionTimeout") int idleSessionTimeout, 
            @JsonProperty("permissions") Set<Permission> permissions){
      this.user = user;
      this.idleSessionTimeout = idleSessionTimeout;
      this.permissions = permissions == null ? null : Collections.unmodifiableSet(permissions);
    }
        
    boolean isAllowed(String resource, String actionName, String association) {
        boolean allowed;
        Map<String, Set<String>> map = allow();
        if (map.containsKey(resource)) {
            allowed = map.get(resource).contains(actionName);
        }
        else {
            map = deny();
            if (map.containsKey(resource)) {
                allowed = !map.get(resource).contains(actionName);
            }
            else {
                GWT.log("unknown resource:" + resource);
                return false;
            }
        }
        Set<String> allowedAssociations = null;
        if (allowed) {
            allowedAssociations = this.associations.get(key(resource, actionName));
            if (allowedAssociations == null) {
                allowedAssociations = this.associations.get(resource);
            }
            if (allowedAssociations != null && association != null) {
                allowed = allowedAssociations.contains(association);
            }
        }
        GWT.log("permission: " + resource + "#" + actionName + 
                (association == null ? "" : "#" + association + allowedAssociations+this.associations) + 
                " -> " + allowed);
        return allowed;
    }
    
    public Set<String> allowedAssocations(String resource, String actionName){
        if (isAllowed(resource, actionName, null)) {
            return this.associations.get(key(resource, actionName));                
        }
        else {
            return this.associations.get(resource);
        }
    }
    
    public Set<String> allowedAssocations(String resource){
        return this.associations.get(resource);
    }
    
    private Map<String, Set<String>> allow(){
        if(allow == null || deny == null){
            createAllowAndDeny();
        }
        return allow;
    }

    private Map<String, Set<String>> deny(){
        if(allow == null || deny == null){
            createAllowAndDeny();
        }
        return deny;
    }

    private void createAllowAndDeny() {
        allow = new HashMap<String, Set<String>>();
        deny = new HashMap<String, Set<String>>();
        associations = new HashMap<String, Set<String>>();
        setupFor(this.permissions);
    }

    protected void setupFor( Set<Permission> permissions)
    {
        for(Permission p: permissions){
            Set<String> actions = new TreeSet<String>();
            for(Action a: p.actions){
                actions.add(a.name.name());
                if (a.associations != null && p.allow){
                    addAssociation(a.associations, key(p.resource, a.name.name()));
                }
            }
            if (p.associations != null){
                addAssociation(p.associations, p.resource);
            }
            if(p.allow){
                allow.put(p.resource, actions);
            }
            else{
                deny.put(p.resource, actions);
            }
            if (p.children != null ){
                setupFor( p.children );
            }
        }
    }

    private void addAssociation(Set<String> associations, String key) {
        Set<String> set;
        if (!this.associations.containsKey(key)){
           set = new TreeSet<String>(); 
           this.associations.put(key, set);
        }
        else {
            set = this.associations.get(key);
        }
        set.addAll(associations);
    }

    private String key(String resource, String actionName) {
        return resource + "#" + actionName;
    }
}
