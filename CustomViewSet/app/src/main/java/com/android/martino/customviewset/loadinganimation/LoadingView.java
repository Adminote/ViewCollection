package com.android.martino.customviewset.loadinganimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.martino.customviewset.R;

import java.util.ArrayList;
import java.util.List;

public class LoadingView extends ProgressDialog{

    private LinearLayout mContainer;
    private Context mContext;
    private List<Runnable> mRunnables = new ArrayList<Runnable>();

    private boolean animationCancel;
    private Handler handler = new Handler();

    public LoadingView(Context context) {
        super(context);
        mContext = context;
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.view_common_transparent_dialog);

        mContainer = (LinearLayout) findViewById(R.id.laoading_layout);
        animationCancel = false;


        for (int i = 0; i < POINT_SIZE; i++) {
            final int j = i;
            final ImageView imageView = new ImageView(mContext);
            imageView.setBackgroundResource(R.drawable.view_white_dot);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);
            imageView.setLayoutParams(params);
            mContainer.addView(imageView);

            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    getAnimator(imageView, true);
                    if (!animationCancel)
                        handler.postDelayed(mRunnables.get(j), ANIM_TIME * POINT_SIZE);
                }
            };
            handler.postDelayed(runnable, ANIM_TIME * i);
            mRunnables.add(runnable);
        }

    }

    private ObjectAnimator getAnimator(View view, boolean isStart) {
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(
                SCALE_X, SCALE_X_FROM, SCALE_X_TO);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(
                SCALE_Y, SCALE_Y_FROM, SCALE_Y_TO);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,
                scaleX, scaleY);
        animator.setRepeatCount(1);
        animator.setInterpolator(new AnticipateInterpolator());
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setDuration(ANIM_TIME);
        if (isStart)
            animator.start();
        return animator;
    }

    @Override
    public void dismiss() {
        animationCancel = true;
        super.dismiss();
    }

    private static final String SCALE_X = "scaleX";
    private static final String SCALE_Y = "scaleY";
    private static final float SCALE_X_FROM = 1.0f;
    private static final float SCALE_X_TO = 2.0f;
    private static final float SCALE_Y_FROM = 1.0f;
    private static final float SCALE_Y_TO = 2.0f;
    private static  final int POINT_SIZE = 10; //圆点数量
    private static final int MARGIN = 10; //原点间距
    private static final int ANIM_TIME = 600; //动画时间
}
