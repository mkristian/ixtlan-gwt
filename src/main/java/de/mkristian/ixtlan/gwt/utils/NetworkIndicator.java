package de.mkristian.ixtlan.gwt.utils;

import com.google.inject.ImplementedBy;

@ImplementedBy( NetworkIndicatorLabel.class )
public interface NetworkIndicator {

    void loading();

    void saving();

    void deleting();

    void finished();

}