package com.android.martino.customviewset.scroll;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.android.martino.customviewset.R;

import java.util.ArrayList;


public class ComplexActivity extends Activity {
    private HorizontalScroll mHorizontalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initViews();
    }

    private void initViews() {
        LayoutInflater inflater = getLayoutInflater();
        mHorizontalView = (HorizontalScroll) findViewById(R.id.container);
        final int width = getDisplayMetrics(this).widthPixels;
        final int height = getDisplayMetrics(this).heightPixels;

        for (int i = 0 ; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(
                    R.layout.viewpager_list, mHorizontalView, false);
            layout.getLayoutParams().width = width;
            layout.getLayoutParams().height = height;

            TextView textView = (TextView) layout.findViewById(R.id.textview);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(Color.rgb(255/(i+1), 255/(i+1), 0));

            createList(layout);
            mHorizontalView.addView(layout);
        }
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {

        WindowManager manager = ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics;
    }

    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.listview);
        ArrayList<String> datas = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }

        MyAdapter adapter = new MyAdapter(this, datas);
        listView.setAdapter(adapter);
    }
}
