package io.github.droidkaigi.confsched.activity;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.droidkaigi.confsched.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class ContributorsActivityTest {

    @Rule
    public IntentsTestRule<ContributorsActivity> activityRule = new IntentsTestRule<>(
            ContributorsActivity.class, true, false);

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = ContributorsActivity.createIntent(context);
        activityRule.launchActivity(intent);

        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void contributors() throws InterruptedException {
        Thread.sleep(3000);
        Spoon.screenshot(activityRule.getActivity(), "contributors_page1");
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(10));
        Thread.sleep(2000);
        Spoon.screenshot(activityRule.getActivity(), "contributors_page2");
    }
}
