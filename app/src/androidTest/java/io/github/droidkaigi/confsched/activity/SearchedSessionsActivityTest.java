package io.github.droidkaigi.confsched.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.droidkaigi.confsched.model.Category;

import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class SearchedSessionsActivityTest {

    @Rule
    public IntentsTestRule<SearchedSessionsActivity> activityRule = new IntentsTestRule<>(
            SearchedSessionsActivity.class, true, false);

    @Before
    public void setUp() {
        // TODO test dataを回せば全部チェックできそう
        Category category = new Category();
        category.name = "test";
        category.id = 1;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = SearchedSessionsActivity.createIntent(context, category);
        activityRule.launchActivity(intent);

        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }


    @Test
    public void searchSessions() {
        Spoon.screenshot(activityRule.getActivity(), "searchSessions");
    }

}