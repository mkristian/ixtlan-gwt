package de.mkristian.ixtlan.gwt.models;


import java.util.Date;

import org.codehaus.jackson.annotate.JsonCreator;
import org.fusesource.restygwt.client.Json;

import de.mkristian.ixtlan.gwt.session.User;

public abstract class AbstractModel implements HasToDisplay, Identifiable {

	private final int id;

	@Json(name = "created_at")
	private final Date createdAt;

	@Json(name = "updated_at")
	private final Date updatedAt;

	@Json(name = "modified_by")
	private final User modifiedBy;
	  
    public AbstractModel() {
        this( 0, null, null, null );
    }

    @JsonCreator
    public AbstractModel( int id, 
            Date createdAt,
            Date updatedAt,
            User modifiedBy ) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.modifiedBy = modifiedBy;
    }

    public int getId(){
      return id;
    }
    
    public Date getCreatedAt(){
      return createdAt;
    }

    public Date getUpdatedAt(){
      return updatedAt;
    }

    public User getModifiedBy(){
      return modifiedBy;
    }
}
