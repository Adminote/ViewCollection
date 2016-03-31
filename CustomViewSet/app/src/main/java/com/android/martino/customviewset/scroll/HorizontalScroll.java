package com.android.martino.customviewset.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;


public class HorizontalScroll extends ViewGroup {
    private static final String TAG = "scroll";

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    private int mChildIndex;
    private int mChildrenSize;

    private int mLastXIntercept;
    private int mLastYIntercept;

    private int mLastX;

    private int mChildWidth;

    private int mScrollXMaxOnce;

    public HorizontalScroll(Context context) {
        super(context);
        init();
    }

    public HorizontalScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (mScroller == null) {
            mScroller = new Scroller(getContext());
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean isIntercept = false;
        int x = (int) ev.getRawX();
        int y = (int) ev.getRawY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isIntercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = mLastXIntercept - x;
                int deltaY = mLastYIntercept - y;
                if (Math.abs(deltaX) > Math.abs(deltaY)) { //向X方向滑动时父控件拦截事件
                    isIntercept = true;
                } else {
                    isIntercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;
                break;
            default:
                break;
        }

        mLastX = x;
        mLastXIntercept = x;
        mLastYIntercept = y;

        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "scrollX:" + getScrollX() + ", min = 0, max = " + mChildWidth * (mChildrenSize-1));

                int deltaX = mLastX - x;
                scrollBy(deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:

                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();//获取近1秒的x方向的滑动速度

                Log.d(TAG, "scrollX:" + getScrollX() + ", x velocity:" + xVelocity);

                if (Math.abs(xVelocity) >= 500) {
                    Log.d(TAG, "x velocity:" + xVelocity);
                    //向右滑为正数，向左滑为负数
                    mChildIndex = xVelocity > 0 ? (mChildIndex - 1) : (mChildIndex + 1);
                    Log.d(TAG, "==index:" +  mChildIndex);
                }
//                else {
//                    //滑动是否超过半屏
//                    mChildIndex = (getScrollX() + mChildWidth / 2) / mChildWidth;
//                    Log.d(TAG, "index:" + mChildIndex);
//                }

                //下标最小为0，最大为mChildren-1
                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildrenSize-1));
                int dx = mChildIndex * mChildWidth - getScrollX();
                smoothScrollBy(dx, 0);
                mVelocityTracker.clear();
                break;
            default:
                break;
        }

        return true;
    }

    private void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(getScrollX(), 0, dx, 0, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//            postInvalidate();
            invalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth;
        int measureHeight;
        final int childCount = getChildCount();
        mChildrenSize = childCount;

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSpaceSise = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measureWidth = childView.getMeasuredWidth() * childCount;
            measureHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measureWidth, measureHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measureWidth = childView.getMeasuredWidth();
            setMeasuredDimension(measureWidth, heightSpaceSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measureHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpaceSise, measureHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View childView = getChildAt(i);

            if (childView.getVisibility() != GONE) {
                final int childWidth = childView.getMeasuredWidth();
                mChildWidth = childWidth;
                mScrollXMaxOnce = childWidth;
                childView.layout(childLeft, 0, childLeft + childWidth, childView.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }
}
