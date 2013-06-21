package de.mkristian.ixtlan.gwt.places;

import com.google.gwt.place.shared.Place;

import de.mkristian.ixtlan.gwt.session.User;

public class PlaceWithSession extends Place implements Cloneable{

    public User currentUser;
    
    public boolean hasSession(){
        return currentUser != null;
    }
    
    public String toString(){
        StringBuilder b = new StringBuilder( getClass().getName() );
        b.append("(");
        if ( hasSession() ){
            b.append( currentUser.toDisplay() );
        }
        else {
            b.append( "-- no user --" );
        }
        b.append(")");
        
        return b.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (hasSession() ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlaceWithSession other = (PlaceWithSession) obj;
        // TODO or use equal ?
        return currentUser == other.currentUser;
    }
}