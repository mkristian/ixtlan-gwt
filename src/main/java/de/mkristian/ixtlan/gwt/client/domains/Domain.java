package de.mkristian.ixtlan.gwt.client.domains;


import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.fusesource.restygwt.client.Json;
import org.fusesource.restygwt.client.Json.Style;

import de.mkristian.ixtlan.gwt.models.HasToDisplay;
import de.mkristian.ixtlan.gwt.models.Identifiable;

@Json(style = Style.RAILS)
public class Domain implements HasToDisplay, Identifiable {

  private final int id;

  private final String name;

  public Domain(){
    this(0, null);
  }
  
  @JsonCreator
  public Domain(@JsonProperty("id") int id, 
          @JsonProperty("name") String name){
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean isNew(){
      return id == 0;
  }
  
  @Override
  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  @Override
  public int hashCode(){
    return id;
  }

  @Override
  public boolean equals(Object other){
    return (other instanceof Domain) && 
        ((Domain)other).id == id;
  }

  @Override
  public String toDisplay() {
    return name;
  }
}
