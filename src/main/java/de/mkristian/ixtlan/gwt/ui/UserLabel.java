/**
 * 
 */
package de.mkristian.ixtlan.gwt.ui;

import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.user.client.ui.ValueLabel;

import de.mkristian.ixtlan.gwt.session.User;

public class UserLabel extends ValueLabel<User> {
 
    static class UserRenderer extends AbstractRenderer<User>{

        public String render(User user) {
            return user == null ? "" : user.getName() + " (" + user.getLogin() + ")";
        }
    }

    private final static UserRenderer RENDERER = new UserRenderer();
    
    public UserLabel() {
        super(RENDERER);
    }        
}