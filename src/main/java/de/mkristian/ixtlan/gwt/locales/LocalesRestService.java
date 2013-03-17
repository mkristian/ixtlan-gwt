package de.mkristian.ixtlan.gwt.locales;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import de.mkristian.ixtlan.gwt.dispatchers.DefaultDispatcherSingleton;


@Options(dispatcher = DefaultDispatcherSingleton.class)
public interface LocalesRestService extends RestService {

  @GET @Path("/locales")
  void index(MethodCallback<List<Locale>> callback);

  @GET @Path("/locales/{id}")
  void show(@PathParam("id") int id, MethodCallback<Locale> callback);

}
