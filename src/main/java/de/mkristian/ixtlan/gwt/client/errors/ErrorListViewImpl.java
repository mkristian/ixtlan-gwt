package de.mkristian.ixtlan.gwt.client.errors;

import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.SHOW;

import java.util.List;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.DateLabel;

import de.mkristian.ixtlan.gwt.readonly.ReadonlyListViewImpl;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyListWidget;

public class ErrorListViewImpl
		extends ReadonlyListViewImpl<Error>
		implements ErrorListView {

	public ErrorListViewImpl() {
		super( new ErrorListWidget() );
	}
	
	static class ErrorListWidget extends ReadonlyListWidget<Error> {
		
	    @Override
	    public void reset( List<Error> models ) {
	        removeAllRows();
	        if ( models != null ) {
	            int row = 0;
	            for( Error model: models ){
	            	DateLabel date = new DateLabel( 
	            			DateTimeFormat.getFormat( PredefinedFormat.DATE_TIME_SHORT) );
	            	date.setValue( model.getCreatedAt() );
	                setWidget( row, 0, date.asWidget() );
					setText( row, 1, model.getMessage() + "" );
					
					setWidget( row, 2, newButton( SHOW, model ) );
	                row++;
	            }
	        }
	    }
	}
}