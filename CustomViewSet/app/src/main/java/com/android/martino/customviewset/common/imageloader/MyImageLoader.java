package com.android.martino.customviewset.common.imageloader;

import com.android.martino.customviewset.common.fresco.MySimpleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MyImageLoader {

    //ImageLoader加载图片
    public static void loadImage(MySimpleImageView view, String uri) {
        ImageLoader.getInstance().displayImage(uri, view);
    }
}
