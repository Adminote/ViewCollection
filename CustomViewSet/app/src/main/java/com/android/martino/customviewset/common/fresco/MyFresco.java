package com.android.martino.customviewset.common.fresco;

import android.net.Uri;

public class MyFresco {

    //Fresco加载图片
    public static void loadImage(MySimpleImageView view, String uri) {
        view.setImageURI(Uri.parse(uri));
    }
}
