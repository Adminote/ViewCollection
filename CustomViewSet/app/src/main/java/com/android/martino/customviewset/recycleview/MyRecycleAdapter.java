package com.android.martino.customviewset.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.martino.customviewset.R;
import com.android.martino.customviewset.common.fresco.MyFresco;
import com.android.martino.customviewset.common.imageloader.MyImageLoader;
import com.android.martino.customviewset.recycleview.model.House;

import java.util.List;

public class MyRecycleAdapter extends BaseAdapter{

    private static final int TYPE_HEAD = 1;
    private static final int TYPE_FOOT = 2;
    private static final int TYPE_CONTENT = 3;

    private Context mContext;
    private List<House> mHouses;

    public MyRecycleAdapter(Context context, List<House> houses) {
        mContext = context;
        mHouses = houses;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return TYPE_HEAD;
        if (position == mHouses.size() + 1) return TYPE_FOOT;
        return TYPE_CONTENT;
    }

    protected int getRealIndex(int position) {
        return position - 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEAD:
                return new MyHeadHolder(LayoutInflater.from(mContext).inflate(
                        R.layout.activity_item_recycle_head, parent, false));
            case TYPE_FOOT:
                return new MyFootHolder(LayoutInflater.from(mContext).inflate(
                        R.layout.activity_item_recycle_foot, parent, false));
        }
        return new MyContentHolder(LayoutInflater.from(mContext).inflate(
                R.layout.activity_item_recycleview, parent, false));
    }

    private void handleHeadType(RecyclerView.ViewHolder holder) {
        MyHeadHolder headHolder = (MyHeadHolder) holder;
        headHolder.head.setText("这是head");
    }

    private void handleFootType(RecyclerView.ViewHolder holder) {
        MyFootHolder footHolder = (MyFootHolder) holder;
        footHolder.foot.setText("这是foot");
    }

    private void handleContentType(final RecyclerView.ViewHolder holder, int position) {
        MyContentHolder contentHolder = (MyContentHolder) holder;
        House house = mHouses.get(getRealIndex(position));
//        MyImageLoader.loadImage(contentHolder.imageView, house.getUrl());
        MyFresco.loadImage(contentHolder.imageView, house.getUrl());

        contentHolder.title.setText(house.getTitle());
        contentHolder.block.setText(house.getBlock());
        contentHolder.price.setText(house.getPrice());

        handleClickEvent(holder);
    }

    protected void handleType(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEAD:
                handleHeadType(holder);
                break;
            case TYPE_FOOT:
                handleFootType(holder);
                break;
            case TYPE_CONTENT:
                handleContentType(holder, position);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        handleType(holder, position);
    }

    @Override
    public int getItemCount() {
        return mHouses.size() + 2;
    }
}
