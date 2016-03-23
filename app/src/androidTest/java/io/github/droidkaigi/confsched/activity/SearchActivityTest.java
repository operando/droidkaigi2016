package io.github.droidkaigi.confsched.activity;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.droidkaigi.confsched.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {

    @Rule
    public IntentsTestRule<SearchActivity> activityRule = new IntentsTestRule<>(SearchActivity.class);

    @Test
    public void search() throws InterruptedException {
        Spoon.screenshot(activityRule.getActivity(), "search_page");
        onView(withId(R.id.edit_search)).perform(typeText("test"), closeSoftKeyboard());
        Thread.sleep(500);
        Spoon.screenshot(activityRule.getActivity(), "search_test");
    }
}
