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

import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class WebViewActivityTest {

    private static final String LICENSE_URL = "file:///android_asset/license.html";

    @Rule
    public IntentsTestRule<WebViewActivity> activityRule = new IntentsTestRule<>(
            WebViewActivity.class, true, false);

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = WebViewActivity.createIntent(context, LICENSE_URL, context.getString(R.string.about_license));
        activityRule.launchActivity(intent);
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void webView() throws InterruptedException {
        Thread.sleep(2500);
        Spoon.screenshot(activityRule.getActivity(), "webView");
    }
}