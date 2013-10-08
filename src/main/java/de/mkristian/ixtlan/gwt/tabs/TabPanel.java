package de.mkristian.ixtlan.gwt.tabs;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.TabBarCss;
import com.googlecode.mgwt.ui.client.util.HandlerRegistrationConverter;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;

public class TabPanel extends Composite implements HasSelectionHandlers<Integer> {
    private FlowPanel container;
    private TabPanel.TabContainer tabContainer;
    private TabPanel.TabBar tabBar;

    public TabPanel() {
        this(MGWTStyle.getTheme().getMGWTClientBundle().getTabBarCss());
    }

    public TabPanel(TabBarCss css) {
        container = new FlowPanel();
        initWidget(container);
        container.addStyleName(css.tabPanel());

        tabContainer = new TabContainer();
        tabContainer.addStyleName(css.tabPanelContainer());

        tabBar = new TabBar(css);

        tabBar.addSelectionHandler(new SelectionHandler<Integer>() {

            @Override
            public void onSelection(SelectionEvent<Integer> event) {
                tabContainer.setSelectedChild(event.getSelectedItem());

            }
        });

        setDisplayTabBarOnTop(MGWT.getOsDetection().isAndroid());

    }

    public void setDisplayTabBarOnTop(boolean top) {
        container.clear();
        if (top) {
            container.add(tabBar);
            container.add(tabContainer);

        } else {
            container.add(tabContainer);
            container.add(tabBar);

        }
    }

    /**
     * <p>
     * setSelectedChild
     * </p>
     * 
     * @param index a int.
     */
    public void setSelectedChild(int index) {
        tabBar.setSelectedButton(index, true);
        tabContainer.setSelectedChild(index);
    }

    public List<TabBarButton> getTabBarButtons(){
        return Collections.unmodifiableList( tabBar.children );
    }
    
    /**
     * <p>
     * add
     * </p>
     * 
     * @param button a
     *            {@link com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase}
     *            object.
     * @param child a {@link com.google.gwt.user.client.ui.Widget} object.
     */
    public void add(TabBarButton button, Widget child) {
        tabContainer.add(child);
        tabBar.add(button);
    }

    /**
     * at the moment there is no support for custom parsers:
     * http://code.google.com/p/google-web-toolkit/issues/detail?id=4461 this is
     * a workaround to allow use with UIBinder
     * 
     * {@link TabPanel#add(TabBarButton, Widget)} if you are writing java
     * code
     * 
     * @param b the tab to add
     */
    @UiChild(tagname = "tabs")
    public void addTab(Tab b) {
        Widget w = b.getWidget();
        TabBarButton button = b.getButton();

        if (button == null) {
            throw new IllegalArgumentException("button can not be null");
        }

        if (w == null) {
            throw new IllegalArgumentException("widget can not be null");
        }
        add(button, w);
    }

    /**
     * <p>
     * remove
     * </p>
     * 
     * @param index a int.
     */
    public void remove(int index) {
        tabContainer.remove(index);
        tabBar.remove(index);
    }

    /**
     * <p>
     * remove
     * </p>
     * 
     * @param w a {@link com.google.gwt.user.client.ui.Widget} object.
     */
    public void remove(Widget w) {
        int childIndex = tabContainer.getChildIndex(w);
        tabContainer.remove(childIndex);
        tabBar.remove(childIndex);
    }

    /** {@inheritDoc} */
    @Override
    public com.google.gwt.event.shared.HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
        return new HandlerRegistrationConverter(tabBar.addSelectionHandler(handler));
    }

    public static class TabBar extends Composite implements HasSelectionHandlers<Integer> {

        private FlowPanel container;
        private LinkedList<TabBarButton> children;
        private LinkedList<HandlerRegistration> handlers = new LinkedList<HandlerRegistration>();
        protected final TabBarCss css;

        public TabBar() {
            this(MGWTStyle.getTheme().getMGWTClientBundle().getTabBarCss());
        }

        public TabBar(TabBarCss css) {
            this.css = css;
            css.ensureInjected();
            children = new LinkedList<TabBarButton>();
            container = new FlowPanel();
            container.setStylePrimaryName(css.tabbar());
            initWidget(container);
        }

        private class InternalTouchHandler implements TapHandler {

            private final TabBarButton button;

            public InternalTouchHandler(TabBarButton button) {
                this.button = button;

            }

            @Override
            public void onTap(TapEvent event) {
                setSelectedButton(getIndexForWidget(button));
            }

        }

        public void add(TabBarButton w) {
            if (children.size() == 0) {
                w.setSelected(true);
            }
            container.add(w);
            children.add(w);
            handlers.add(w.addTapHandler(new InternalTouchHandler(w)));

        }

        public void clear() {
            container.clear();
            children.clear();
            handlers.clear();

        }

        private int getIndexForWidget(TabBarButton w) {
            return children.indexOf(w);
        }

        public boolean remove(TabBarButton w) {
            int indexForWidget = getIndexForWidget(w);
            children.remove(w);
            if (indexForWidget != -1) {
                handlers.get(indexForWidget).removeHandler();
                handlers.remove(indexForWidget);
            }

            return container.remove(w);

        }

        public void setSelectedButton(int index) {
            setSelectedButton(index, false);
        }

        public void setSelectedButton(int index, boolean suppressEvent) {
            if (index < 0) {
                throw new IllegalArgumentException("invalud index");
            }

            if (index >= children.size()) {
                throw new IllegalArgumentException("invalud index");
            }
            int count = 0;
            for (TabBarButton button : children) {
                if (count == index) {
                    button.setSelected(true);
                } else {
                    button.setSelected(false);
                }
                count++;
            }
            if (!suppressEvent)
                SelectionEvent.fire(this, Integer.valueOf(index));
        }

        @Override
        public com.google.gwt.event.shared.HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
            return addHandler(handler, SelectionEvent.getType());
        }

        /**
         * @param index
         */
        public void remove(int index) {
            TabBarButton w = getWidgetForIndex(index);
            remove(w);
        }

        /**
         * @param index
         * @return
         */
        private TabBarButton getWidgetForIndex(int index) {
            return children.get(index);
        }

    }

    public static class TabContainer extends Composite {

        private SimplePanel container;
        private LinkedList<Widget> children = new LinkedList<Widget>();
        private Widget activeWidget;

        public TabContainer() {
            container = new SimplePanel();
            initWidget(container);

        }

        public void add(Widget w) {
            if (children.size() == 0) {
                container.setWidget(w);
                activeWidget = w;
                if (activeWidget instanceof ScrollPanel) {
                    activeWidget.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());
                }
            }
            children.add(w);

        }

        public void clear() {
            children.clear();
            container.setWidget(null);
            activeWidget = null;

        }

        public void setSelectedChild(int index) {
            activeWidget = children.get(index);
            if (activeWidget instanceof ScrollPanel) {
                activeWidget.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());
            }
            container.setWidget(activeWidget);
        }

        public boolean remove(Widget w) {
            int index = getChildIndex(w);
            boolean remove = children.remove(w);
            if (w == activeWidget) {
                if (activeWidget instanceof ScrollPanel) {
                    activeWidget.removeStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());
                }
                activeWidget = null;
                if (index - 1 >= 0) {
                    setSelectedChild(index);
                }
            }

            return remove;
        }

        /**
         * @param w
         */
        public int getChildIndex(Widget w) {

            return children.indexOf(w);

        }

        /**
         * @param childIndex
         */
        public void remove(int childIndex) {
            Widget w = getWidgetForIndex(childIndex);
            remove(w);
        }

        /**
         * @param childIndex
         * @return
         */
        private Widget getWidgetForIndex(int childIndex) {
            return children.get(childIndex);
        }
    }
}