package io.github.droidkaigi.confsched.activity;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.droidkaigi.confsched.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> activityRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void top() {
        Spoon.screenshot(activityRule.getActivity(), "top");
    }

    @Test
    public void my_schedule() throws InterruptedException {
        onView(withId(R.id.drawer)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_my_schedule));
        Thread.sleep(1000);
        Spoon.screenshot(activityRule.getActivity(), "my_schedule");
    }

    @Test
    public void map() throws InterruptedException {
        onView(withId(R.id.drawer)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_map));
        Thread.sleep(1000);
        Spoon.screenshot(activityRule.getActivity(), "map");
    }

    @Test
    public void settings() throws InterruptedException {
        onView(withId(R.id.drawer)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_settings));
        Thread.sleep(1000);
        Spoon.screenshot(activityRule.getActivity(), "settings");
    }

    @Test
    public void sponsors() throws InterruptedException {
        onView(withId(R.id.drawer)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_sponsors));
        Thread.sleep(1000);
        Spoon.screenshot(activityRule.getActivity(), "sponsors");
    }

    @Test
    public void about() throws InterruptedException {
        onView(withId(R.id.drawer)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_about));
        Thread.sleep(1000);
        Spoon.screenshot(activityRule.getActivity(), "about");
    }
}