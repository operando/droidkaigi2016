package io.github.droidkaigi.confsched.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import org.parceler.Parcels;

import javax.inject.Inject;

import io.github.droidkaigi.confsched.R;
import io.github.droidkaigi.confsched.fragment.SessionDetailFragment;
import io.github.droidkaigi.confsched.model.Session;
import io.github.droidkaigi.confsched.util.AnalyticsTracker;

public class SessionDetailActivity extends BaseActivity {

    @Inject
    AnalyticsTracker analyticsTracker;

    public static Intent createIntent(@NonNull Context context, @NonNull Session session) {
        Intent intent = new Intent(context, SessionDetailActivity.class);
        intent.putExtra(Session.class.getSimpleName(), Parcels.wrap(session));
        return intent;
    }

    static void startForResult(@NonNull Activity activity, @NonNull Session session, int requestCode) {
        Intent intent = createIntent(activity, session);
        activity.startActivityForResult(intent, requestCode);
    }

    static void startForResult(@NonNull Fragment fragment, @NonNull Session session, int requestCode) {
        Intent intent = createIntent(fragment.getContext(), session);
        fragment.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_session_detail);
        getComponent().inject(this);

        Session session = Parcels.unwrap(getIntent().getParcelableExtra(Session.class.getSimpleName()));
        replaceFragment(SessionDetailFragment.create(session));
    }

    private void replaceFragment(Fragment fragment) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_view, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        analyticsTracker.sendScreenView("session_detail");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
