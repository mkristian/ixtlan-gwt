package de.mkristian.ixtlan.gwt.tabs;

import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class Tab extends Widget {
    private Widget widget;
    private TabBarButton button;

    /**
     * <p>Constructor for Tab.</p>
     */
    public Tab() {
        setElement(DOM.createDiv());
    }

    /**
     * <p>Setter for the field <code>button</code>.</p>
     *
     * @param button a {@link com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase} object.
     */
    @UiChild(limit = 1, tagname = "button")
    public void setButton(TabBarButton button) {
        this.button = button;

    }

    /**
     * <p>Setter for the field <code>widget</code>.</p>
     *
     * @param w a {@link com.google.gwt.user.client.ui.Widget} object.
     */
    @UiChild(limit = 1, tagname = "widget")
    public void setWidget(Widget w) {
        this.widget = w;

    }

    /**
     * <p>Getter for the field <code>widget</code>.</p>
     *
     * @return a {@link com.google.gwt.user.client.ui.Widget} object.
     */
    public Widget getWidget() {
        return widget;
    }

    /**
     * <p>Getter for the field <code>button</code>.</p>
     *
     * @return a {@link com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase} object.
     */
    public TabBarButton getButton() {
        return button;
    }
}