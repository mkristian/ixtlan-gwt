package de.mkristian.ixtlan.gwt.views;

import java.util.List;

import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;

import de.mkristian.ixtlan.gwt.models.Identifiable;
import de.mkristian.ixtlan.gwt.places.RestfulActionEnum;

public abstract class CRUDListViewImpl<T extends Identifiable> 
		extends DetailListViewImpl<T> 
		implements CRUDListView<T> {

    public CRUDListViewImpl( ListWidget<T> list ) {
    	super( list );
        ButtonBar footer = new ButtonBar();     
        footer.add( newButton );
        footer.add( reloadButton );
        
        main.add( footer );
        
        newButton.setVisible( true );
	}

    public void reset( List<T> models, RestfulActionEnum permission  ) {
    	((CRUDListWidget<T>) list).reset( models, permission );
    }
    
	public void remove( T model ) {
        String id = model.getId() + "";
        for(int i = 0; i < list.getRowCount(); i++){
            if(list.getText(i, 0).equals(id)){
                list.removeRow(i);
                return;
            }
        }
    }
}