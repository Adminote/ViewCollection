package com.android.martino.customviewset.loadinganimation;

import android.app.Activity;
import android.content.res.Configuration;

public class LoadingActivity extends Activity {

    private LoadingView loadingView;

    @Override
    protected void onResume() {
        super.onResume();
        loadingView = new LoadingView(this);
        loadingView.show();
    }

    @Override
    public void onBackPressed() {
        if (loadingView.isShowing())
            loadingView.dismiss();

        finish();
    }
}
