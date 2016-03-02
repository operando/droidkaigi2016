package io.github.droidkaigi.confsched.activity;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {

    @Rule
    public IntentsTestRule<SearchActivity> activityRule = new IntentsTestRule<>(SearchActivity.class);

    @Test
    public void search() {
        Spoon.screenshot(activityRule.getActivity(), "search_page1");
    }
}
