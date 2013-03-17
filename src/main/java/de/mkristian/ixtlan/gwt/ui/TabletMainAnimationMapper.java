package de.mkristian.ixtlan.gwt.ui;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;

public class TabletMainAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
	    return Animation.FLIP;
	}
//		if (oldPlace == null) {
//			return Animation.FADE;
//		}
//
//		// animation
//
//		if (oldPlace instanceof AnimationSlidePlace && newPlace instanceof AnimationPlace) {
//			return Animation.SLIDE_REVERSE;
//		}
//
//		if (newPlace instanceof AnimationSlideUpPlace) {
//			return Animation.SLIDE_UP;
//		}
//
//		if (oldPlace instanceof AnimationSlideUpPlace && newPlace instanceof AnimationPlace) {
//			return Animation.SLIDE_UP_REVERSE;
//		}
//
//		if (newPlace instanceof AnimationDissolvePlace) {
//			return Animation.DISSOLVE;
//		}
//
//		if (oldPlace instanceof AnimationDissolvePlace && newPlace instanceof AnimationPlace) {
//			return Animation.DISSOLVE_REVERSE;
//		}
//
//		if (newPlace instanceof AnimationFadePlace) {
//			return Animation.FADE;
//		}
//
//		if (oldPlace instanceof AnimationFadePlace && newPlace instanceof AnimationPlace) {
//			return Animation.FADE_REVERSE;
//		}
//		if (newPlace instanceof AnimationFlipPlace) {
//			return Animation.FLIP;
//		}
//
//		if (oldPlace instanceof AnimationFlipPlace && newPlace instanceof AnimationPlace) {
//			return Animation.FLIP_REVERSE;
//		}
//
//		if (newPlace instanceof AnimationPopPlace) {
//			return Animation.POP;
//		}
//
//		if (oldPlace instanceof AnimationPopPlace && newPlace instanceof AnimationPlace) {
//			return Animation.POP_REVERSE;
//		}
//
//		if (newPlace instanceof AnimationSwapPlace) {
//			return Animation.SWAP;
//		}
//
//		if (oldPlace instanceof AnimationSwapPlace && newPlace instanceof AnimationPlace) {
//			return Animation.SWAP_REVERSE;
//		}
//
//		//		if (oldPlace instanceof AnimationCubePlace && newPlace instanceof AnimationPlace) {
//		//			return Animation.CUBE_REVERSE;
//		//		}
//		//
//		//		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationCubePlace) {
//		//			System.out.println("asdfasdf");
//		//			return Animation.CUBE;
//		//		}
//
//		return Animation.SLIDE;
//	}

}
