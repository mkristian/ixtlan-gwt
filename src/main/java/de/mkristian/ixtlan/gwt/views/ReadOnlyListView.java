package de.mkristian.ixtlan.gwt.views;

import java.util.List;

public interface ReadOnlyListView<T> extends DetailListView {
    void reset(List<T> models);
}