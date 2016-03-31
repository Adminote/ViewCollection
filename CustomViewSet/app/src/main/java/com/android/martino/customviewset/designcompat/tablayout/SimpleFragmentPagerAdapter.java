package com.android.martino.customviewset.designcompat.tablayout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.martino.customviewset.R;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles;
    private int[] tabIcons;
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm,Context context, String[] titles, int[] icons) {
        super(fm);
        this.tabTitles = titles;
        this.tabIcons = icons;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    //自定义view
    public View getTabView(int position){
        View view = LayoutInflater.from(context).inflate(R.layout.view_tab_item, null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        tv.setText(tabTitles[position]);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(tabIcons[position]);
        return view;
    }
}
