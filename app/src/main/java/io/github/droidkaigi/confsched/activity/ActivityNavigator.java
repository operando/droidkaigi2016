package io.github.droidkaigi.confsched.activity;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import javax.inject.Singleton;

import io.github.droidkaigi.confsched.model.Session;

@Singleton
public class ActivityNavigator {

    public void showSessionDetail(@NonNull Activity activity, @NonNull Session session, int requestCode) {
        SessionDetailActivity.startForResult(activity, session, requestCode);
    }

    public void showSessionDetail(@NonNull Fragment fragment, @NonNull Session session, int requestCode) {
        SessionDetailActivity.startForResult(fragment, session, requestCode);
    }

    public void showMain(@NonNull Activity activity, boolean shouldRefresh) {
        MainActivity.start(activity, shouldRefresh);
    }

    public void showWebView(@NonNull Context context, @NonNull String url, @NonNull String title) {
        WebViewActivity.start(context, url, title);
    }

    public void showSearch(@NonNull Fragment fragment, int requestCode) {
        SearchActivity.start(fragment, requestCode);
    }

    public void showFeedback(@NonNull Context context, @NonNull Session session) {
        SessionFeedbackActivity.start(context, session);
    }

}
