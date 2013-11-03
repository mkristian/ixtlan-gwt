package de.mkristian.ixtlan.gwt.ui;

import com.google.gwt.view.client.ProvidesKey;

class EnumKeyProvider<T extends Enum<?>> implements ProvidesKey<T> {
    public Object getKey(T item) {
        return item == null ? -1 : item.ordinal();
    }
}
