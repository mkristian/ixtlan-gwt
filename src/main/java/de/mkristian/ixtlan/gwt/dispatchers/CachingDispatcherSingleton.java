package de.mkristian.ixtlan.gwt.dispatchers;

import org.fusesource.restygwt.client.Dispatcher;
import org.fusesource.restygwt.client.Method;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;

public class CachingDispatcherSingleton implements Dispatcher {

    // we just want this singleton instance nothing else !!!
    public static Dispatcher INSTANCE = DispatcherFactory.INSTANCE.cachingXSRFProtectionDispatcher();

    // do not allow concrete instances of this class
    private CachingDispatcherSingleton(){
        throw new Error("never called");
    }

    // dummy
    public Request send(Method method, RequestBuilder builder)
            throws RequestException {
        return null;
    }
}
