/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.mkristian.ixtlan.gwt.menu;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;

import de.mkristian.ixtlan.gwt.session.Guard;
import de.mkristian.ixtlan.gwt.ui.BasicCell;


/**
 * @author Daniel Kurka
 * @author kristian
 */
@Singleton
public class MenuViewImpl implements MenuView {

    private final HeaderButton forwardButton;
    private final HeaderPanel headerPanel;
	private final LayoutPanel main;
	private final CellList<Item> cellList;
    private final Guard guard;
    private final List<Item> items = new LinkedList<Item>();
    
	@Inject
	public MenuViewImpl( final Guard guard ) {
	    this.guard = guard;
		this.main = new LayoutPanel();
		headerPanel = new HeaderPanel();

        forwardButton = new HeaderButton();
        forwardButton.setForwardButton(true);
        if (MGWT.getOsDetection().isPhone()) {
            headerPanel.setRightWidget(forwardButton);
        }
        main.add(headerPanel);
        
		this.cellList = new CellList<Item>( new BasicCell<Item>() {

			@Override
			public String getDisplayString( Item model ) {
				return model.getDisplayString();
			}

			@Override
			public boolean canBeSelected( Item model ) {
				return true;
			}
		} );
		this.cellList.setRound(true);

        ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setWidget( this.cellList );
		scrollPanel.setScrollingEnabledX( false );

		this.main.add(scrollPanel);

	}

    protected void clearItems(){
        items.clear();
    }

	protected void addItem( Item item ){
	    items.add( item );
	}
	
	@Override
	public Widget asWidget() {
		return main;
	}


    @Override
    public void setTitle(String text) {
        headerPanel.setCenter(text);

    }
    
    @Override
    public void setRightButtonText(String text) {
        forwardButton.setText(text);

    }

	@Override
	public HasCellSelectedHandler getList() {
		return cellList;
	}

	@Override
	public List<Item> renderItems() {
	    List<Item> allowedItems = new LinkedList<Item>();
	    for( Item item : items ){
	        if ( guard.isAllowed( item.getRestfulPlace() == null ? null : item.getRestfulPlace().resourceName,
	                item.getRestfulAction() ) ){
	            allowedItems.add( item );
	        }
	    }
		cellList.render( allowedItems );
		return allowedItems;
	}

	@Override
	public void setSelectedIndex( int index, boolean selected ) {
		cellList.setSelectedIndex( index, selected );
	}
}
