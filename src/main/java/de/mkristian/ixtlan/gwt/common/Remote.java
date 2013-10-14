package de.mkristian.ixtlan.gwt.common;

import org.fusesource.restygwt.client.Method;

public interface Remote<T> {

    void fireRetrieve(Method method, T model);

    void fireError(Method method, Throwable e);
}