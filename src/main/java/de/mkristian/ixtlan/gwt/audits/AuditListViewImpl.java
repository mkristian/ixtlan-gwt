package de.mkristian.ixtlan.gwt.audits;

import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.SHOW;

import java.util.List;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.DateLabel;

import de.mkristian.ixtlan.gwt.views.ReadOnlyListViewImpl;
import de.mkristian.ixtlan.gwt.views.ReadOnlyListWidget;

public class AuditListViewImpl
		extends ReadOnlyListViewImpl<Audit>
		implements AuditListView {

	public AuditListViewImpl() {
		super( new AuditListWidget() );
	}

	static class AuditListWidget extends ReadOnlyListWidget<Audit> {
	
	    public void reset( List<Audit> models ) {
	        removeAllRows();
	        if ( models != null ) {
	            int row = 0;
	            for( Audit model: models ){
	            	DateLabel date = new DateLabel( 
	            			DateTimeFormat.getFormat( PredefinedFormat.DATE_TIME_SHORT) );
	            	date.setValue( model.getCreatedAt() );
	            	setWidget( row, 0, date.asWidget() );
					setText( row, 1, "[" + model.getLogin() + "]" );
					setText( row, 2, model.getMessage() + "" );
					
					setWidget( row, 3, newButton( SHOW, model ) );
	                row++;
	            }
	        }
	    }
	}
}