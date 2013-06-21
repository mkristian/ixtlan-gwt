package de.mkristian.ixtlan.gwt.configuration;


import java.util.Date;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.fusesource.restygwt.client.Json;

import de.mkristian.ixtlan.gwt.models.HasToDisplay;
import de.mkristian.ixtlan.gwt.session.User;

public class Configuration implements HasToDisplay {

  @Json(name = "created_at")
  private final Date createdAt;

  @Json(name = "updated_at")
  private final Date updatedAt;

  @Json(name = "modified_by")
  private final User modifiedBy;

  @Json(name = "base_url")
  private String baseUrl;
  
  @Json(name = "errors_keep_dumps")
  private int errorsKeepDumps;
  
  @Json(name = "errors_from_email")
  private String errorsFromEmail;

  @Json(name = "errors_to_emails")
  private String errorsToEmails;

  @Json(name = "idle_session_timeout")
  private int idleSessionTimeout;

  @Json(name = "audit_keep_logs")
  private int auditKeepLogs;

  @Json(name = "users_url")
  private String usersUrl;

  @Json(name = "users_token")
  private String usersToken;

  public Configuration(){
    this(null, null, null);
  }

  @JsonCreator
  public Configuration(@JsonProperty("createdAt") Date createdAt, 
          @JsonProperty("updatedAt") Date updatedAt,
          @JsonProperty("modifiedBy") User modifiedBy){
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.modifiedBy = modifiedBy;
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

  public int getErrorsKeepDumps(){
    return errorsKeepDumps;
  }

  public void setErrorsKeepDumps(int value){
    errorsKeepDumps = value;
  }

  public String getErrorsFromEmail(){
    return errorsFromEmail;
  }

  public void setErrorsFromEmail(String value){
    errorsFromEmail = value;
  }

  public String getErrorsToEmails(){
    return errorsToEmails;
  }

  public void setErrorsToEmails(String value){
    errorsToEmails = value;
  }

  public int getIdleSessionTimeout(){
    return idleSessionTimeout;
  }

  public void setIdleSessionTimeout(int value){
    idleSessionTimeout = value;
  }

  public int getAuditKeepLogs(){
    return auditKeepLogs;
  }

  public void setAuditKeepLogs(int value){
    auditKeepLogs = value;
  }
  
  public String getBaseUrl() {
	return baseUrl;
  }

  public void setBaseUrl(String baseUrl) {
	this.baseUrl = baseUrl;
  }

  public String getUsersUrl() {
	return usersUrl;
  }

  public void setUsersUrl(String usersUrl) {
	this.usersUrl = usersUrl;
  }

  public String getUsersToken() {
	return usersToken;
  }

  public void setUsersToken(String usersToken) {
	this.usersToken = usersToken;
  }

  public String toDisplay() {
    return "Configuration( idle_session_timeout=" + idleSessionTimeout + " )";
  }
}
