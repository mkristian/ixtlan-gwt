package de.mkristian.ixtlan.gwt.ui;

import java.io.IOException;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler;
import com.googlecode.mgwt.mvp.client.history.HTML5HistorianLegacyImpl.DefaultHistorian;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;
import com.googlecode.mgwt.ui.client.widget.base.MValueBoxBase;
import com.googlecode.mgwt.ui.client.widget.touch.GestureUtility;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidgetImpl;

import de.mkristian.ixtlan.gwt.utils.IxtlanGWTStyle;
import de.mkristian.ixtlan.gwt.utils.ModelLinkCss;


/**
 * A simple box with clickable/touchable text with anchor link 
 */
public class MAnchorLinkBox
        extends MValueBoxBase<ModelLink> {

    public static class ModelLinkRenderer implements Renderer<ModelLink> {

      @Override
      public String render(ModelLink object) {
        return "TODO" + object.text;
      }

      @Override
      public void render(ModelLink object, Appendable appendable) throws IOException {
        appendable.append("TODO: " + object.text);

      }
    }

    public static class ModelLinkParser implements Parser<ModelLink> {

      @Override
      public ModelLink parse(CharSequence text) {
          return null;
      }
    }
    
    private static class ModelLinkValueBoxBase
            extends ValueBoxBase<ModelLink> 
            implements HasSource, HasTapHandlers, HasTouchHandlers {

        private static final TouchWidgetImpl impl = GWT.create(TouchWidgetImpl.class);

      private Object source;
      private ModelLink link;
      protected final GestureUtility gestureUtility;

      
      ModelLinkValueBoxBase( ModelLinkCss linkCss ){
          super( DOM.createInputText(),
                 new ModelLinkRenderer(),
                 new ModelLinkParser() );
        gestureUtility = new GestureUtility( this );
        addStyleName( linkCss.box() );
      }
      
      @Override
      public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
          return impl.addTouchStartHandler(this, handler);

      }

      @Override
      public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
          return impl.addTouchMoveHandler(this, handler);

      }

      @Override
      public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
          return impl.addTouchCancelHandler(this, handler);

      }

      @Override
      public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
          return impl.addTouchEndHandler(this, handler);

      }

      @Override
      public HandlerRegistration addTouchHandler(TouchHandler handler) {
          HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();

          handlerRegistrationCollection.addHandlerRegistration(addTouchCancelHandler(handler));
          handlerRegistrationCollection.addHandlerRegistration(addTouchStartHandler(handler));
          handlerRegistrationCollection.addHandlerRegistration(addTouchEndHandler(handler));
          handlerRegistrationCollection.addHandlerRegistration(addTouchMoveHandler(handler));
          return handlerRegistrationCollection;
      }
      public HandlerRegistration addTapHandler(TapHandler handler) {
          gestureUtility.ensureTapRecognizer();
          return addHandler(handler, TapEvent.getType());
      }

      @Override
      protected HandlerManager createHandlerManager() {
        return new HandlerManager(source);
      }

      public void setSource(Object source) {
        this.source = source;
      }
      
      @Override

      public void setValue( ModelLink value, boolean fireEvents) {
          super.setValue( value , fireEvents );
          this.link = value;
      }
    }

    public MAnchorLinkBox() {
        this(MGWTStyle.getTheme().getMGWTClientBundle().getInputCss());

    }

    public MAnchorLinkBox( InputCss css ) {
        this(css, 
             IxtlanGWTStyle.getIxtlanGWTClientBundle().getModelLinkCss());

    }
    public MAnchorLinkBox( InputCss css, ModelLinkCss linkCss ) {
        this( css, 
              linkCss,
              new DefaultHistorian());

    }

    protected MAnchorLinkBox( InputCss css, ModelLinkCss linkCss,
                              final Historian historian) {
        super( css, new ModelLinkValueBoxBase( linkCss ) );
        linkCss.ensureInjected();
        addStyleName( linkCss.textBox() );
        addStyleName( css.textBox() );
        ((ModelLinkValueBoxBase)box).addTapHandler( new TapHandler() {
            
            @Override
            public void onTap( TapEvent event )
            {
                historian.newItem( getAnchor(), true );
            }
        } );
    }
    
    private String getAnchor(){
        return ((ModelLinkValueBoxBase)box).link.anchor;
    }
    
}