package de.mkristian.ixtlan.gwt.session;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import de.mkristian.ixtlan.gwt.dispatchers.RestfulDispatcherSingleton;

@Path("/session")
@Options(dispatcher = RestfulDispatcherSingleton.class)
public interface SessionRestService extends RestService {

    @POST @Consumes("application/json")
    void create(Authentication authentication, MethodCallback<Session> callback);

    @DELETE
    void destroy(MethodCallback<Void> callback);

    @POST @Path("/reset_password")
    void resetPassword(Authentication authentication, MethodCallback<Void> methodCallback);
}
