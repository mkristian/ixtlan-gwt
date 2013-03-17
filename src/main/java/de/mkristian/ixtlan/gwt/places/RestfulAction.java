package de.mkristian.ixtlan.gwt.places;

public interface RestfulAction {

    String token();

    String name();// will be implemented by java.lang.Enum class

    boolean viewOnly();
}
