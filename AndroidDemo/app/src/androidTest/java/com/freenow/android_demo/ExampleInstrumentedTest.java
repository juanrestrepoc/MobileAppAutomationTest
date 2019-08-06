package com.freenow.android_demo;

import android.content.Context;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.clearText;


import com.freenow.android_demo.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void failedLoginWithWrongCredentialsShowsMessageInSnackbar() throws InterruptedException {
        onView(withId(R.id.edt_username))
                .perform(click(),
                        clearText(),
                        typeTextIntoFocusedView("Hello"),
                        closeSoftKeyboard());

        onView(withId(R.id.edt_password))
                .perform(click(),
                        clearText(),
                        typeTextIntoFocusedView("Hello"),
                        closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());

        onView(withText(R.string.message_login_fail)).check(matches(isDisplayed()));
    }
}