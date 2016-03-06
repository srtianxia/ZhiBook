package com.srtianxia.zhibook.utils.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.srtianxia.zhibook.R;

/**
 * Created by srtianxia on 2016/3/4.
 */
public class PlayerDisc extends RelativeLayout {
    private static final String TAG = "PlayerDisc";
    private ImageView imgDiscBg;
    private ImageView imgDiscImage;
    private Context context;

    private boolean isPlaying = false;

    private float continueAnimationValue;

    private ObjectAnimator animator;

    public PlayerDisc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imgDiscImage = (ImageView) findViewById(R.id.img_disc_image);
        imgDiscBg = (ImageView) findViewById(R.id.img_disc_bg);

    }

    public PlayerDisc(Context context) {
        super(context);
    }

    public PlayerDisc(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void start(){
        startAnimator(0.0f);
    }
    public void pause() {
        if (animator.isRunning() || animator.isStarted()) {
            animator.cancel();
        }
    }

    public void next() {

    }

    public void continuePlay() {
        startAnimator(continueAnimationValue);
    }

    private void startAnimator(float value){
        animator = ObjectAnimator.ofFloat(imgDiscBg,"rotation",value, 360 + value);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                continueAnimationValue = (float) animation.getAnimatedValue();
            }
        });
        animator.setDuration(5000).setInterpolator(new LinearInterpolator());
        if (animator.isRunning() || animator.isStarted()) {
            animator.cancel();
        }
        animator.start();
    }
}
