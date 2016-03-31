package com.android.martino.customviewset.transitioncompat.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.android.martino.customviewset.R;
import com.android.martino.customviewset.transitioncompat.fragment.FromFragment;

public class FromActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, FromFragment.newInstance());
        transaction.commit();

    }
}
