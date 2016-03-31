package com.android.martino.customviewset.transitioncompat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.android.martino.customviewset.R;
import com.android.martino.customviewset.transitioncompat.SharedElementTransitionManager;
import com.android.martino.customviewset.transitioncompat.fragment.ToFragment;

public class ToActivity extends FragmentActivity {

    public static void startActivityWithSettingCompat(Activity activity) {
        Intent intent = new Intent(activity, ToActivity.class);

        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.activity_to_container, ToFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        SharedElementTransitionManager sharedElementTransitionManager = SharedElementTransitionManager.get();
        sharedElementTransitionManager.runExitAnimation(new Runnable() {
            public void run() {
                finish();
            }
        });
    }
}
