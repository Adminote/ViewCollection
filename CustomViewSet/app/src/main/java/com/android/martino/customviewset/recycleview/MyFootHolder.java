package com.android.martino.customviewset.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.martino.customviewset.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyFootHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.foot)
    TextView foot;

    public MyFootHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
