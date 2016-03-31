package com.android.martino.customviewset;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class MyApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        //初始化fresco
        Fresco.initialize(this);

        //初始化imageloader
        ImageLoader.getInstance().init(
                ImageLoaderConfiguration.createDefault(
                        getApplicationContext()));
    }
}
