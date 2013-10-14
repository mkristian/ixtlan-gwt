package de.mkristian.ixtlan.gwt.client.audits;

import static de.mkristian.ixtlan.gwt.places.RestfulActionEnum.SHOW;

import java.util.List;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.DateLabel;

import de.mkristian.ixtlan.gwt.readonly.ReadonlyListViewImpl;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyListWidget;

public class AuditListViewImpl
		extends ReadonlyListViewImpl<Audit>
		implements AuditListView {

	public AuditListViewImpl() {
		super( new AuditListWidget() );
	}

	static class AuditListWidget extends ReadonlyListWidget<Audit> {
	
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