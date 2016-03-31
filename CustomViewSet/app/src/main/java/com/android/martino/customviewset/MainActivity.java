package com.android.martino.customviewset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.martino.customviewset.loadinganimation.LoadingActivity;
import com.android.martino.customviewset.designcompat.tablayout.TabLayoutActivity;
import com.android.martino.customviewset.materialdesign.appbarlayout.HideActivity;
import com.android.martino.customviewset.recycleview.RecycleActivity;
import com.android.martino.customviewset.transitioncompat.activity.FromActivity;
import com.android.martino.customviewset.scroll.ComplexActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.column1)
    void click1() {
        startActivity(new Intent(MainActivity.this, TabLayoutActivity.class));
    }

    @OnClick(R.id.column2)
    void click2() {
        startActivity(new Intent(MainActivity.this, LoadingActivity.class));
    }

    @OnClick(R.id.column3)
    void click3() {  startActivity(new Intent(MainActivity.this, FromActivity.class));   }

    @OnClick(R.id.column4)
    void click4() {
        startActivity(new Intent(MainActivity.this, ComplexActivity.class));
    }

    @OnClick(R.id.column5)
    void click5() {
        startActivity(new Intent(MainActivity.this, RecycleActivity.class));
    }

    @OnClick(R.id.column6)
    void click6() {
        startActivity(new Intent(MainActivity.this, HideActivity.class));
    }
}
