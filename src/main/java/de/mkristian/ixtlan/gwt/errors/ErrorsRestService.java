package de.mkristian.ixtlan.gwt.errors;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import de.mkristian.ixtlan.gwt.dispatchers.DefaultDispatcherSingleton;


@Options(dispatcher = DefaultDispatcherSingleton.class)
public interface ErrorsRestService extends RestService {

  @GET @Path("/errors")
  void index(MethodCallback<List<Error>> callback);

  @GET @Path("/errors/{id}")
  void show(@PathParam("id") int id, MethodCallback<Error> callback);

}
