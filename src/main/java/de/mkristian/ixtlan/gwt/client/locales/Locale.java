package de.mkristian.ixtlan.gwt.client.locales;


import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.fusesource.restygwt.client.Json;
import org.fusesource.restygwt.client.Json.Style;

import de.mkristian.ixtlan.gwt.models.HasToDisplay;
import de.mkristian.ixtlan.gwt.models.Identifiable;


@Json(style = Style.RAILS)
public class Locale implements HasToDisplay, Identifiable {

  private final int id;

  private final String code;

  public Locale(){
    this(0, null);
  }
  
  @JsonCreator
  public Locale(@JsonProperty("id") int id, 
          @JsonProperty("code") String code){
    this.id = id;
    this.code = code;
  }

  @Override
  public boolean isNew(){
      return id == 0;
  }
  
  @Override
  public int getId(){
    return id;
  }

  public String getCode(){
    return code;
  }

  @Override
  public int hashCode(){
    return id;
  }

  @Override
  public boolean equals(Object other){
    return (other instanceof Locale) && 
        ((Locale)other).id == id;
  }

  @Override
  public String toDisplay() {
    return code;
  }
}
