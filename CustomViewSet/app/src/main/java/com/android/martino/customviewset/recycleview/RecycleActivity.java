package com.android.martino.customviewset.recycleview;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.android.martino.customviewset.R;
import com.android.martino.customviewset.common.utils.ModelUtil;
import com.android.martino.customviewset.recycleview.model.House;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecycleActivity extends Activity {

    @Bind(R.id.recycleview)
    RecyclerView recyclerView;

    MyRecycleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        ButterKnife.bind(this);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //线性设置
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));

        //网格设置
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        adapter = new MyRecycleAdapter(RecycleActivity.this, ModelUtil.getInitHouse());
        adapter.setOnItemClickLitener(new BaseAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(recyclerView, "点击了第" + position + "个", Snackbar.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }


}
