package com.android.martino.customviewset.scroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.android.martino.customviewset.R;

import java.util.List;


public class MyAdapter extends BaseAdapter {
    private List<String> mData;
    private Context mContext;

    public MyAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.viewpager_list_item, parent, false);
        Button imageButton = (Button) convertView.findViewById(R.id.text);
        imageButton.setText(mData.get(position));
        return convertView;
    }
}
