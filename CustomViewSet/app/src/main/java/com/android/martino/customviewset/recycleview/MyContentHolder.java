package com.android.martino.customviewset.recycleview;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.martino.customviewset.R;
import com.android.martino.customviewset.common.fresco.MySimpleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyContentHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.imageView)
    MySimpleImageView imageView;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.block)
    TextView block;

    @Bind(R.id.price)
    TextView price;

    public MyContentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
