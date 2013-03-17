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
package de.mkristian.ixtlan.gwt.ui;

import de.mkristian.ixtlan.gwt.places.RestfulAction;
import de.mkristian.ixtlan.gwt.places.RestfulPlace;


/**
 * @author Daniel Kurka
 * @author kristian 
 */
public class Item {
    
	private final String displayString;
	private final RestfulAction action;
	private final RestfulPlace<?,?> place;
	
	public Item( String displayString ) {
        this( displayString, null, null );
    }
    public Item( String displayString, 
            RestfulPlace<?,?> place ) {
        this( displayString, place, place.action );
    }
    
	public Item( String displayString, 
	        RestfulPlace<?,?> place,
	        RestfulAction action ) {
		this.displayString = displayString;
		this.place = place;
		this.action = action;
	}

	/**
	 * @return the displayString
	 */
	public String getDisplayString() {
		return displayString;
	}

	public RestfulPlace<?,?> getRestfulPlace() {
		return place;
	}
    public RestfulAction getRestfulAction() {
        return action;
    }
}
