package de.mkristian.ixtlan.gwt.tabs;

import com.google.gwt.core.shared.GWT;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.TabBarCss;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;

import de.mkristian.ixtlan.gwt.utils.IxtlanGWTStyle;
import de.mkristian.ixtlan.gwt.utils.TabBarButtonCss;

public class TabBarButton extends ButtonBase {

    protected final TabBarCss css;

    public TabBarButton( String text ){
       this( MGWTStyle.getTheme().getMGWTClientBundle().getTabBarCss(),
             IxtlanGWTStyle.getIxtlanGWTClientBundle().getTabBarButtonCss(),
             text );
    }
    
    public TabBarButton(TabBarCss mgwtCss, TabBarButtonCss css, String text) {
        super(mgwtCss);
        css.ensureInjected();
        this.css = mgwtCss;
        addStyleName(this.css.button());
        GWT.log( css.text() );
        getElement().addClassName(css.text());
        setText( text );
    }

    /**
     * <p>
     * setSelected
     * </p>
     * 
     * @param selected a boolean.
     */
    public void setSelected(boolean selected) {
        if (selected) {
            addStyleName(css.selected());
        } else {
            removeStyleName(css.selected());
        }
    }

}