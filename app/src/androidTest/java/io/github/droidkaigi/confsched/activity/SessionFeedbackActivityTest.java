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

import io.github.droidkaigi.confsched.R;
import io.github.droidkaigi.confsched.model.Session;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class SessionFeedbackActivityTest {

    private static final Session SESSION = new Session();

    static {
        SESSION.title = "Android Dev Tools Knowledge";
    }

    @Rule
    public IntentsTestRule<SessionFeedbackActivity> activityRule = new IntentsTestRule<>(
            SessionFeedbackActivity.class, true, false);

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = SessionDetailActivity.createIntent(context, SESSION);
        activityRule.launchActivity(intent);

        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void a() {
        Spoon.screenshot(activityRule.getActivity(), "test");
        onView(withId(R.id.submit_feedback_button)).perform(scrollTo());
        Spoon.screenshot(activityRule.getActivity(), "test1");
    }
}
