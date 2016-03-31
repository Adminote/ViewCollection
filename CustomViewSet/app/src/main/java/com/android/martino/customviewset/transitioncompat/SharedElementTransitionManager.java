package com.android.martino.customviewset.transitioncompat;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class SharedElementTransitionManager {

    private static SharedElementTransitionManager sInstance;
    private static long ANIMATION_DURATION_TIME;

    private int mFromViewLeft;
    private int mFromViewTop;
    private int mFromViewWidth;
    private int mFromViewHeight;
    private int mLeftDelta;
    private int mTopDelta;
    private float mWidthScale;
    private float mHeightScale;
    private ImageView mToView;
    private View mBackgroundView;

    public static void with(long durationTime) {
        sInstance = new SharedElementTransitionManager(durationTime);
    }

    public static SharedElementTransitionManager get() {
        if (sInstance == null) {
            throw new RuntimeException(
                    "ElementStateKeeper is not instantiate yet. You must be call `with(context)` first.");
        }
        return sInstance;
    }

    public SharedElementTransitionManager(long durationTime) {
        ANIMATION_DURATION_TIME = durationTime;
    }

    public void setFromView(ImageView imageView) {
        int[] screenLocation = new int[2];
        imageView.getLocationOnScreen(screenLocation);

        mFromViewLeft = screenLocation[0];
        mFromViewTop = screenLocation[1];
        mFromViewWidth = imageView.getWidth();
        mFromViewHeight = imageView.getHeight();
    }

    public void applyTransitionToView(ImageView imageView, final View backgroundView, Bundle savedInstanceState) {

        mToView = imageView;

        if (savedInstanceState == null) {
            ViewTreeObserver observer = imageView.getViewTreeObserver();
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mToView.getViewTreeObserver().removeOnPreDrawListener(this);

                    int[] screenLocation = new int[2];
                    mToView.getLocationOnScreen(screenLocation);
                    mLeftDelta = mFromViewLeft - screenLocation[0];
                    mTopDelta = mFromViewTop - screenLocation[1];
                    mWidthScale = (float) mFromViewWidth / mToView.getWidth();
                    mHeightScale = (float) mFromViewHeight / mToView.getHeight();

                    if (backgroundView != null) {
                        mBackgroundView = backgroundView;
                        runBgAnimation(true);
                    }
                    runEnterAnimation();
                    return true;
                }
            });
        }
    }

    private void runEnterAnimation() {
        final long duration = ANIMATION_DURATION_TIME;

        mToView.setPivotX(0);
        mToView.setPivotY(0);
        mToView.setScaleX(mWidthScale);
        mToView.setScaleY(mHeightScale);
        mToView.setTranslationX(mLeftDelta);
        mToView.setTranslationY(mTopDelta);

        mToView.animate().setDuration(duration).scaleX(1).scaleY(1).translationX(0)
                .translationY(0).setInterpolator(new DecelerateInterpolator());

    }

    public void runExitAnimation(final Runnable runnable) {
        final long duration = ANIMATION_DURATION_TIME;

        mToView.setPivotX(0);
        mToView.setPivotY(0);

        runBgAnimation(false);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            mToView.animate().setDuration(duration).
                    scaleX(mWidthScale).scaleY(mHeightScale).
                    translationX(mLeftDelta).translationY(mTopDelta)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            runnable.run();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                        }
                    });
        } else {
            mToView.animate().setDuration(duration).
                    scaleX(mWidthScale).scaleY(mHeightScale).
                    translationX(mLeftDelta).translationY(mTopDelta)
                    .withEndAction(runnable);
        }
    }

    private void runBgAnimation(boolean fromTranslucent) {
        if (mBackgroundView == null) {
            return;
        }

        Drawable background = (Drawable) mBackgroundView.getBackground();
        ObjectAnimator bgAnim;
        if (fromTranslucent) {
            bgAnim = ObjectAnimator.ofInt(background, "alpha", 0, 255);
        } else {
            bgAnim = ObjectAnimator.ofInt(background, "alpha", 255, 0);
        }
        bgAnim.setDuration(ANIMATION_DURATION_TIME);
        bgAnim.start();
    }

}
