package com.freenow.android_demo;

import android.content.Context;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.contrib.DrawerActions.close;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.clearText;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.Matchers.allOf;
import static android.support.test.espresso.contrib.DrawerActions.open;

import com.freenow.android_demo.activities.MainActivity;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private String username;
    private String password;
    private String optionName;
    private String optionPhoneNumber;

    @Before
    public void setTestData() {
        username = "crazydog335";
        password = "venture";
        optionName = "Sarah Scott";
    }

    @Test
    public void loginUserLooksForSecondDriverOnListAndCallsDriver() throws Exception {

        onView(withId(R.id.edt_username))
                .perform(click(),
                        clearText(),
                        typeTextIntoFocusedView(username),
                        closeSoftKeyboard());

        onView(withId(R.id.edt_password))
                .perform(click(),
                        clearText(),
                        typeTextIntoFocusedView(password),
                        closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());

        Thread.sleep(5000);

        onView(withId(R.id.drawer_layout)).perform(open());

        onView(withId(R.id.nav_username))
                .check(matches(withText(username)));
        onView(withId(R.id.drawer_layout)).perform(close());

        Thread.sleep(5000);

        onView(withId(R.id.textSearch))
                .perform(click(),
                        clearText(),
                        typeTextIntoFocusedView("sa"),
                        closeSoftKeyboard());
        onView(withText(optionName))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        onView(withText(optionName))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }
}