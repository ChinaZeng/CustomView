package com.zzw.customview.view.JumpLoadView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by zzw on 2017/6/7.
 * Version:
 * Des:
 */

public class JumpLoadView extends LinearLayout {

    private int mRadio = 30;
    private static final int JUMP_MAX_HEIGHT = 200;
    private static final int JUMP_UP_TIME = 600;

    private ShapeView mShapeView;
    private ArcView mArcView;
    private int mFlog;

    private AnimatorSet mAnimatorSet;
    private Activity mActivity;

    public JumpLoadView(Context context) {
        this(context, null);
    }

    public JumpLoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JumpLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mActivity = (Activity) context;

        setOrientation(VERTICAL);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setGravity(Gravity.CENTER);
        mShapeView = new ShapeView(context);
        mArcView = new ArcView(context);

        mShapeView.setRadio(mRadio);
        mArcView.setRadio(mRadio);

        addView(mShapeView);
        addView(mArcView);

        startAnim();
    }



    public void startAnim() {
        if (mAnimatorSet == null) {
            mAnimatorSet = new AnimatorSet();
            //up
            ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(mShapeView, "rotation", 0.0f, 360.0f);
            rotationAnim.setDuration(JUMP_UP_TIME);
            rotationAnim.setInterpolator(new AccelerateDecelerateInterpolator());//先快后慢

            ObjectAnimator translationAnimUp = ObjectAnimator.ofFloat(mShapeView, "translationY", 0, -JUMP_MAX_HEIGHT);
            translationAnimUp.setDuration(JUMP_UP_TIME);
            translationAnimUp.setInterpolator(new AccelerateDecelerateInterpolator());//先快后慢

            ObjectAnimator scaleXAnimUp = ObjectAnimator.ofFloat(mArcView, "scaleX", 1.0f, 0.2f);
            scaleXAnimUp.setDuration(JUMP_UP_TIME);
            scaleXAnimUp.setInterpolator(new AccelerateDecelerateInterpolator());//先快后慢

            ObjectAnimator scaleYAnimUp = ObjectAnimator.ofFloat(mArcView, "scaleY", 1.0f, 0.2f);
            scaleYAnimUp.setDuration(JUMP_UP_TIME);
            scaleYAnimUp.setInterpolator(new AccelerateDecelerateInterpolator());//先快后慢

            //down
            ObjectAnimator translationAnimDown = ObjectAnimator.ofFloat(mShapeView, "translationY", -JUMP_MAX_HEIGHT, 0);
            translationAnimDown.setDuration(JUMP_UP_TIME);
            translationAnimDown.setInterpolator(new AccelerateInterpolator());//先慢后快

            ObjectAnimator scaleXAnimXDown = ObjectAnimator.ofFloat(mArcView, "scaleX", 0.2f, 1.0f);
            scaleXAnimXDown.setDuration(JUMP_UP_TIME);
            scaleXAnimXDown.setInterpolator(new AccelerateInterpolator());//先慢后快

            ObjectAnimator scaleYAnimDown = ObjectAnimator.ofFloat(mArcView, "scaleY", 0.2f, 1.0f);
            scaleYAnimDown.setDuration(JUMP_UP_TIME);
            scaleYAnimDown.setInterpolator(new AccelerateInterpolator());//先慢后快

            mAnimatorSet.play(translationAnimUp)
                    .with(rotationAnim)
                    .with(scaleXAnimUp)
                    .with(scaleYAnimUp)
                    .before(translationAnimDown)
                    .before(scaleXAnimXDown)
                    .before(scaleYAnimDown);
            mAnimatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mFlog++;//圆形 0-->1方形 -->2 三角形 -->3->0---->
                    if (mFlog > 2) {
                        mFlog = 0;
                    }
                    mShapeView.setFlog(mFlog);
                    startAnim();
                }
            });
        }
        mAnimatorSet.start();
    }

    public void stopAnim() {
        if (mAnimatorSet != null)
            mAnimatorSet.cancel();
    }

    public void setRadio(int radio) {
        this.mRadio = radio;
        mShapeView.setRadio(radio);
        mArcView.setRadio(radio);
    }



    @Override
    protected void onAttachedToWindow() {
        mActivity.getApplication().registerActivityLifecycleCallbacks(animLifecyleCallback);
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        mActivity.getApplication().unregisterActivityLifecycleCallbacks(animLifecyleCallback);
        super.onDetachedFromWindow();
    }

    private SimpleActivityLifecycleCallbacks animLifecyleCallback = new SimpleActivityLifecycleCallbacks() {
        @Override
        public void onActivityResumed(Activity activity) { // 页面第一次启动的时候不会执行
            if (activity == mActivity)
                startAnim();
            super.onActivityResumed(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {
            if (activity == mActivity)
                stopAnim();
            super.onActivityPaused(activity);
        }
    };

}
