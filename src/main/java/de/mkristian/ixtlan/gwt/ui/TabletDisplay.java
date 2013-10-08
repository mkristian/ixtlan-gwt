package de.mkristian.ixtlan.gwt.ui;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.googlecode.mgwt.ui.client.dialog.TabletPortraitOverlay;
import com.googlecode.mgwt.ui.client.layout.MasterRegionHandler;
import com.googlecode.mgwt.ui.client.layout.OrientationRegionHandler;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;

import de.mkristian.ixtlan.gwt.menu.Item;
import de.mkristian.ixtlan.gwt.menu.MenuView;

@Singleton
public class TabletDisplay {

    private SimplePanel navContainer = new SimplePanel();

    private SimplePanel mainContainer = new SimplePanel();

    private final MenuView menu;

    private final PlaceController placeController;
    
    @Inject
    public TabletDisplay( EventBus eventBus, PlaceController placeController, MenuView menu ){
        this.placeController = placeController;
        this.menu = menu;
        navContainer.getElement().setId("nav");
        navContainer.getElement().addClassName("landscapeonly");
        
        AnimatableDisplay navDisplay = GWT.create(AnimatableDisplay.class);

        final TabletPortraitOverlay tabletPortraitOverlay = new TabletPortraitOverlay();

        new OrientationRegionHandler(navContainer, tabletPortraitOverlay,
                navDisplay);
        new MasterRegionHandler(eventBus, "nav", tabletPortraitOverlay);

//        ActivityMapper navActivityMapper = new TabletNavActivityMapper(
//                clientFactory);
//
//        AnimationMapper navAnimationMapper = new TabletNavAnimationMapper();
//
//        AnimatingActivityManager navActivityManager = new AnimatingActivityManager(
//                navActivityMapper, navAnimationMapper,
//                eventBus );
//
//        navActivityManager.setDisplay(navDisplay);

        navContainer.setWidget( menu.asWidget() );
        menu.renderItems();
        mainContainer.getElement().setId("main");
        AnimatableDisplay mainDisplay = GWT.create(AnimatableDisplay.class);

        TabletMainActivityMapper tabletMainActivityMapper = new TabletMainActivityMapper();

        AnimationMapper tabletMainAnimationMapper = new TabletMainAnimationMapper();

        AnimatingActivityManager mainActivityManager = new AnimatingActivityManager(
                tabletMainActivityMapper, tabletMainAnimationMapper,
                eventBus);

        mainActivityManager.setDisplay(mainDisplay);
        mainContainer.setWidget(mainDisplay);
    }
    
    public void init( HasWidgets root ){
        root.add(navContainer);
        
        //root.add(mainContainer);
        
        // TODO login/logout/login might just collect CellSelectedHandler
        final List<Item> items = menu.renderItems();
        menu.getList().addCellSelectedHandler(new CellSelectedHandler() {
            
            @Override
            public void onCellSelected(CellSelectedEvent event) {
                Item item = items.get( event.getIndex() );
                placeController.goTo( item.getRestfulPlace() );
            }
        });
    }
}
