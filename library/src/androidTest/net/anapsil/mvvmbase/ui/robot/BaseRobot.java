package net.anapsil.mapsac.ui.robots;

import android.content.Intent;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import net.anapsil.mapsac.ui.matchers.CustomMatchers;
import net.anapsil.mapsac.ui.viewActions.ClickDrawableAction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by ana.silva on 14/07/17.
 */

public abstract class BaseRobot {

    public abstract BaseRobot initActivity(IntentsTestRule activityTestRule, Intent intent);

    BaseRobot closeKeyboard() {
        Espresso.closeSoftKeyboard();

        return this;
    }

    BaseRobot isViewVisible(@IdRes int id) {
        onView(withId(id)).check(matches(isDisplayed()));

        return this;
    }

    BaseRobot isViewVisible(String text) {
        onView(withText(text)).check(matches(isDisplayed()));

        return this;
    }

    BaseRobot isTextVisible(@StringRes int id) {
        onView(withText(id)).check(matches(isDisplayed()));

        return this;
    }

    BaseRobot isHintVisible(@StringRes int id) {
        onView(withHint(id)).check(matches(isDisplayed()));

        return this;
    }

    BaseRobot clickViewWithId(@IdRes int id) {
        onView(withId(id)).perform(click());

        return this;
    }

    BaseRobot clickViewWithText(String text) {
        onView(withText(text)).perform(click());

        return this;
    }

    BaseRobot clickViewWithText(@IdRes int resId) {
        onView(withText(resId)).perform(click());

        return this;
    }

    BaseRobot typeTextOnViewWithId(@IdRes int id, String text) {
        onView(withId(id)).perform(typeText(text));

        return this;
    }

    BaseRobot typeTextOnViewWithIdAndSubmit(@IdRes int id, String text) {
        onView(withId(id)).perform(typeText(text), pressImeActionButton());

        return this;
    }

    BaseRobot isErrorMessageVisible(@IdRes int id, String message) {
        onView(withId(id)).check(matches(CustomMatchers.hasErrorText(message)));
        return this;
    }

    BaseRobot isErrorMessageVisible(@IdRes int id, int resId) {
        onView(withId(id)).check(matches(CustomMatchers.hasErrorText(resId)));
        return this;
    }

    BaseRobot isErrorMessageNotVisible(@IdRes int id, int resId) {
        onView(withId(id)).check(matches(not(CustomMatchers.hasErrorText(resId))));
        return this;
    }

    BaseRobot clickCompoundDrawable(@IdRes int id, @ClickDrawableAction.Location int location) {
        onView(withId(id)).perform(new ClickDrawableAction((location)));
        return this;
    }

    BaseRobot isTextViewEmpty(@IdRes int id) {
        onView(withId(id)).check(matches(withText("")));

        return this;
    }

    BaseRobot isSnackBarMessageVisible(String message) {
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(message))).check(matches(isDisplayed()));

        return this;
    }
}
