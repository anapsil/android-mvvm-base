package net.anapsil.mapsac.ui.matchers;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static org.hamcrest.Matchers.allOf;


/**
 * Created by ana.silva on 02/10/2017.
 */

public class CustomMatchers {
    static String expectedErrorText;

    public static Matcher<View> hasErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (!(item instanceof TextInputLayout))
                    return false;

                CharSequence error = ((TextInputLayout) item).getError();

                return error != null && expectedErrorText.equals(error.toString());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with error: ");
                description.appendText(expectedErrorText);
            }
        };
    }

    public static Matcher<View> hasErrorText(@StringRes final int resourceId) {

        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (!(item instanceof TextInputLayout))
                    return false;

                CharSequence error = ((TextInputLayout) item).getError();
                expectedErrorText = item.getResources().getString(resourceId);

                return error != null && expectedErrorText.equals(error.toString());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with error: ");
                description.appendText(expectedErrorText);
            }
        };
    }

    public static ViewAction clickDrawables() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(TextView.class), new BoundedMatcher<View, TextView>(TextView.class) {
                    @Override
                    protected boolean matchesSafely(final TextView tv) {
                        if (tv.requestFocusFromTouch())//get focus so drawables become visible
                            for (Drawable d : tv.getCompoundDrawables())//if the textview has drawables then return a match
                                if (d != null)
                                    return true;

                        return false;
                    }

                    @Override
                    public void describeTo(Description description) {
                        description.appendText("has drawable");
                    }
                });
            }

            @Override
            public String getDescription() {
                return "click drawables";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView) view;
                if (tv != null && tv.requestFocusFromTouch())//get focus so drawables are visible
                {
                    Drawable[] drawables = tv.getCompoundDrawables();

                    Rect tvLocation = new Rect();
                    tv.getHitRect(tvLocation);

                    Point[] tvBounds = new Point[4];//find textview bound locations
                    tvBounds[0] = new Point(tvLocation.left, tvLocation.centerY());
                    tvBounds[1] = new Point(tvLocation.centerX(), tvLocation.top);
                    tvBounds[2] = new Point(tvLocation.right, tvLocation.centerY());
                    tvBounds[3] = new Point(tvLocation.centerX(), tvLocation.bottom);

                    for (int location = 0; location < 4; location++)
                        if (drawables[location] != null) {
                            Rect bounds = drawables[location].getBounds();
                            tvBounds[location].offset(bounds.width() / 2, bounds.height() / 2);//get drawable click location for left, top, right, bottom
                            if (tv.dispatchTouchEvent(MotionEvent.obtain(android.os.SystemClock.uptimeMillis(), android.os.SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, tvBounds[location].x, tvBounds[location].y, 0)))
                                tv.dispatchTouchEvent(MotionEvent.obtain(android.os.SystemClock.uptimeMillis(), android.os.SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, tvBounds[location].x, tvBounds[location].y, 0));
                        }
                }
            }
        };
    }
}
