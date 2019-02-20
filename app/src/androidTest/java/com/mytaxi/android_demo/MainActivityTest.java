package com.mytaxi.android_demo;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final String USER_NAME;// = "crazydog335";
    private static final String PASSWORD;

    static {
        PASSWORD = "venture";
        USER_NAME = "crazydog335";
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");
    private MainActivity mActivity = null;

    @Before
    public void setActivity() {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void searchAndCallDriver() {

        login(USER_NAME, PASSWORD);
        Espresso.onView((withId(R.id.textSearch))).perform(ViewActions.typeText("sa"));
        // Check that both suggestions are displayed.
        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        // Tap on a suggestion.
        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());

        // assertions to check displayed driver is "Sarah Scott".
        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.fab)).perform(ViewActions.click());
    }

    public void login(String User_Name, String Password) {
        Espresso.onView((withId(R.id.edt_username))).perform(ViewActions.typeText(User_Name), closeSoftKeyboard());
        Espresso.onView((withId(R.id.edt_password))).perform(ViewActions.typeText(Password), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btn_login)).perform(ViewActions.click());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
