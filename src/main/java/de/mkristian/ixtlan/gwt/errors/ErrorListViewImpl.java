package de.mkristian.ixtlan.gwt.errors;

import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.SHOW;

import java.util.List;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.DateLabel;

import de.mkristian.ixtlan.gwt.views.ReadOnlyListViewImpl;

public class ErrorListViewImpl
		extends ReadOnlyListViewImpl<Error>
		implements ErrorListView {

    public void reset( List<Error> models ) {
        list.removeAllRows();
        if ( models != null ) {
            int row = 0;
            for( Error model: models ){
            	DateLabel date = new DateLabel( 
            			DateTimeFormat.getFormat( PredefinedFormat.DATE_TIME_SHORT) );
            	date.setValue( model.getCreatedAt() );
                list.setWidget( row, 0, date.asWidget() );
				list.setText( row, 1, model.getMessage() + "" );
				
				list.setWidget( row, 2, newButton( SHOW, model ) );
                row++;
            }
        }
    }
}