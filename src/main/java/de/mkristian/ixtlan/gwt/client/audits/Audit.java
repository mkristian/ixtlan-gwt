package de.mkristian.ixtlan.gwt.client.audits;


import java.util.Date;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.fusesource.restygwt.client.Json;
import org.fusesource.restygwt.client.Json.Style;

import de.mkristian.ixtlan.gwt.models.HasToDisplay;
import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.session.User;

@Json(style = Style.RAILS)
public class Audit implements HasToDisplay, Identifiable {

  public final int id;

  @Json(name = "created_at")
  private final Date createdAt;

  @Json(name = "created_by")
  private final User createdBy;
  
  private String login;

  private String message;

  @Json( name = "http_method" )
  private String method;

  private String path;
  
  public Audit(){
    this(0, null, null);
  }
  
  @JsonCreator
  public Audit(@JsonProperty("id") int id, 
          @JsonProperty("createdAt") Date createdAt,
          @JsonProperty("createdBy") User createdBy){
    this.id = id;
    this.createdAt = createdAt;
    this.createdBy = createdBy;
  }

  @Override
  public boolean isNew(){
      return id == 0;
  }
  
  @Override
  public int getId(){
    return id;
  }

  public Date getCreatedAt(){
    return createdAt;
  }

  public User getCreatedBy(){
    return createdBy;
  }

  public String getLogin(){
    return login;
  }

  public void setLogin(String value){
    login = value;
  }

  public String getMessage(){
    return message;
  }

  public void setMessage(String value){
    message = value;
  }

  public String getMethod() {
	return method;
  }

  public void setMethod(String method) {
	this.method = method;
  }

  public String getPath() {
	return path;
  }

  public void setPath(String path) {
	this.path = path;
  }

  public String getHttpInfo() {
	  return method + " " + path;
  }
  
  @Override
  public int hashCode(){
    return id;
  }

  @Override
  public boolean equals(Object other){
    return (other instanceof Audit) && 
        ((Audit)other).id == id;
  }

  @Override
  public String toDisplay() {
    return login;
  }
}
