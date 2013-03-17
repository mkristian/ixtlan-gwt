/**
 *
 */
package de.mkristian.ixtlan.gwt.utils;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Singleton;

@Singleton
public class DisplayMessage extends FlowPanel {

    private final HTML notice = new HTML();
    
    private final PopupPanel popup = new PopupPanel(true);

    private final DisplayMessageCss css;
    
    public DisplayMessage() {
        this( IxtlanGWTStyle.getIxtlanGWTClientBundle().getDisplayMessageCss() );
    }
    
    public DisplayMessage( DisplayMessageCss css ) {
        this.css = css;
        this.css.ensureInjected();
        popup.setStylePrimaryName( css.popup() );
        popup.setWidget(notice);
        popup.hide();
        popup.setVisible(false);
    }

    private void show( final String msg ) {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            
            public void execute() {                
                popup.setVisible(true);
                if (msg == null) {
                    popup.hide();
                } 
                else {
                    SafeHtmlBuilder buf = new SafeHtmlBuilder();
                    buf.appendEscapedLines(msg.trim());
                    notice.setHTML(buf.toSafeHtml());
                    popup.show();
                }
            }
        });
    }
    
    public void info( String msg ){
        show( msg );
        notice.setStylePrimaryName( this.css.info() );
    }

    public void warn( String msg ){
        show( msg );
        notice.setStylePrimaryName( this.css.warn() );
    }
    
    public void error( String msg ){
        show( msg );
        notice.setStylePrimaryName( this.css.error() );
    }

    public void error( String msg, Throwable e ){
        error( msg + " - " + e.getMessage() );
    }

    public void hide(){
        popup.hide();
    }
 }