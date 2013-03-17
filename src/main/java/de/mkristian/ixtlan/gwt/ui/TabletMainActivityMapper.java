package de.mkristian.ixtlan.gwt.ui;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class TabletMainActivityMapper implements ActivityMapper {

//	private final ClientFactory clientFactory;

	//private Place lastPlace;

	public TabletMainActivityMapper(){//ClientFactory clientFactory) {
	//	this.clientFactory = clientFactory;

	}

	@Override
	public Activity getActivity(Place place) {
		//Activity activity = getActivity(lastPlace, place);
		//lastPlace = place;
		//return activity;
	    return null;
	}
//
//	private AboutActivity aboutActivity;
//
//	private AboutActivity getAboutActivity() {
//		if (aboutActivity == null) {
//			aboutActivity = new AboutActivity(clientFactory);
//		}
//
//		return aboutActivity;
//	}
//
//	private Activity getActivity(Place lastPlace, Place newPlace) {
//		if (newPlace instanceof HomePlace) {
//			return getAboutActivity();
//		}
//
//		if (newPlace instanceof AboutPlace) {
//			return getAboutActivity();
//		}
//
//		if (newPlace instanceof UIPlace) {
//			return getAboutActivity();
//		}
//
//		if (newPlace instanceof ScrollWidgetPlace) {
//			return new ScrollWidgetActivity(clientFactory);
//		}
//
//		if (newPlace instanceof ElementsPlace) {
//			return new ElementsActivity(clientFactory);
//		}
//
//		if (newPlace instanceof FormsPlace) {
//			return new FormsActivity(clientFactory);
//		}
//
//		if (newPlace instanceof ButtonBarPlace) {
//			return new ButtonBarActivity(clientFactory);
//		}
//
//		if (newPlace instanceof SearchBoxPlace) {
//			return new SearchBoxActivity(clientFactory);
//		}
//
//		if (newPlace instanceof TabBarPlace) {
//			return new TabBarActivity(clientFactory);
//		}
//
//		if (newPlace instanceof ButtonPlace) {
//			return new ButtonActivity(clientFactory);
//		}
//
//		if (newPlace instanceof PopupPlace) {
//			return new PopupActivity(clientFactory);
//		}
//
//		if (newPlace instanceof ProgressBarPlace) {
//			return new ProgressBarActivity(clientFactory);
//		}
//
//		if (newPlace instanceof ProgressIndicatorPlace) {
//			return new ProgressIndicatorActivity(clientFactory);
//		}
//
//		if (newPlace instanceof SliderPlace) {
//			return new SliderActivity(clientFactory);
//		}
//
//		if (newPlace instanceof AnimationPlace) {
//			return new AboutActivity(clientFactory);
//		}
//
//		if (newPlace instanceof PullToRefreshPlace) {
//			return new PullToRefreshActivity(clientFactory);
//		}
//
//		if (newPlace instanceof CarouselPlace) {
//			return new CarouselActivity(clientFactory);
//		}
//
//		if (newPlace instanceof GroupedCellListPlace) {
//			return new GroupedCellListActivity(clientFactory);
//		}
//
//		if (newPlace instanceof AnimationSlidePlace || newPlace instanceof AnimationSlideUpPlace || newPlace instanceof AnimationDissolvePlace || newPlace instanceof AnimationFadePlace
//				|| newPlace instanceof AnimationFlipPlace || newPlace instanceof AnimationPopPlace || newPlace instanceof AnimationSwapPlace || newPlace instanceof AnimationCubePlace) {
//			return new AnimationDoneActivity(clientFactory);
//		}
//
//		return null;
//	}

}
