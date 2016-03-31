package com.android.martino.customviewset.materialdesign.appbarlayout;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.martino.customviewset.R;
import com.android.martino.customviewset.common.utils.ModelUtil;
import com.android.martino.customviewset.recycleview.BaseAdapter;
import com.android.martino.customviewset.recycleview.DividerItemDecoration;
import com.android.martino.customviewset.recycleview.MyRecycleAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HideActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recycleview)
    RecyclerView recyclerView;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    MyRecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbarlayout);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        collapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.black));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //线性设置
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));

        //网格设置
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        adapter = new MyRecycleAdapter(HideActivity.this, ModelUtil.getInitHouse());
        adapter.setOnItemClickLitener(new BaseAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(recyclerView, "点击了第" + position + "个", Snackbar.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
