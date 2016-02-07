package com.srtianxia.zhibook.utils.ui;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by srtianxia on 2016/2/7.
 */
public class YAnimation {
    public static void toBottom(View view){
        float mY = view.getY();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"y",mY+500f);
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    public static void toTop(View view){
        float mY = view.getY();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"y",mY-500f);
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
