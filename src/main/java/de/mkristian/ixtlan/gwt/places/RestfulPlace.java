package de.mkristian.ixtlan.gwt.places;



import com.google.gwt.activity.shared.Activity;

import de.mkristian.ixtlan.gwt.models.HasToDisplay;


public abstract class RestfulPlace<T, S> extends PlaceWithSession {

    public final RestfulAction action;

    public final int id;

    public final String resourceName;

    public final T model;

    private RestfulPlace<?, ?> parent;
    
    public RestfulPlace(int id, T model, RestfulAction restfulAction, String name) {
        this.action = restfulAction;
        this.id = id;
        this.model = model;
        this.resourceName = name;
    }
    
    public RestfulPlace<?, ?> getParent()
    {
        if( this.parent == null ){
            this.parent = createParentPlace();
        }
        return parent;
    }
    
    protected RestfulPlace<?, ?> createParentPlace() {
        return null;
    }
 
    public void setParent( RestfulPlace<?, ?> parent )
    {
        this.parent = parent;
    }

    public String token(){
        StringBuilder buf = new StringBuilder( getParent() == null ? "" : getParent().token() );
        buf.append( Token2RestfulPlaceMapper.SEPARATOR ).append( resourceName );
        if ( id > 0 ){
            buf.append( Token2RestfulPlaceMapper.SEPARATOR ).append( id );
        }
        if ( action.token().length() > 0 ){
            buf.append( Token2RestfulPlaceMapper.SEPARATOR ).append( action.token() );
        }
        return buf.toString();
    }
    
    public String title(){
        // TODO StringBuilder
        // TODO singular vs plural
        String title = "";
        if ( getParent() != null ){
            title = getParent().title() + " - ";
        }
        title += resourceName.substring( 0, 1 ).toUpperCase() + resourceName.substring( 1 );
        if ( this.action instanceof RestfulActionEnum ) {
            switch( RestfulActionEnum.valueOf( this.action ) ){
            case INDEX:
                return title;// + "s";
            case NEW:
                return title + " - new Entry";
            case DESTROY:
            case EDIT:
            case SHOW:
                if ( model != null && model instanceof HasToDisplay ){
                    return title + " - " + ((HasToDisplay) model ).toDisplay();
                }
            default:
            }
        }
        return title;
    }
    
    public abstract Activity create(S factory);
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((action == null) ? 0 : action.name().hashCode());
        result = prime * result + id;
        result = prime * result + (hasSession() ? 1231 : 1237);
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result
                + ((resourceName == null) ? 0 : resourceName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        RestfulPlace<?, ?> other = (RestfulPlace<?, ?>) obj;
        if (action == null) {
            if (other.action != null)
                return false;
        } else if (!action.name().equals(other.action.name()))
            return false;
        if (id != other.id)
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (resourceName == null) {
            if (other.resourceName != null)
                return false;
        } else if (!resourceName.equals(other.resourceName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Place[action=" + action + ", id=" + id + ", model="
                + model + ", resourceName=" + resourceName + ", parent=" + parent + "]";
    }
}