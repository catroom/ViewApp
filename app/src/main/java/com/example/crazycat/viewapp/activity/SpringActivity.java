package com.example.crazycat.viewapp.activity;

import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.crazycat.viewapp.R;

public class SpringActivity extends AppCompatActivity {
    private float downX, downY;
    private SeekBar damping, stiffness;
    private VelocityTracker velocityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring);
//        findViewById(android.R.id.content).setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        stiffness = (SeekBar) findViewById(R.id.stiffness);
        stiffness.setMax((int)SpringForce.STIFFNESS_HIGH);
        stiffness.setProgress((int) SpringForce.STIFFNESS_LOW);
        damping = (SeekBar) findViewById(R.id.damping);
        damping.setProgress((int)(SpringForce.DAMPING_RATIO_HIGH_BOUNCY * 100));
        final ImageView box = (ImageView) findViewById(R.id.box);
        final SpringAnimation animX = new SpringAnimation(box, SpringAnimation.SCALE_X, 0.5f);
        final SpringAnimation animY = new SpringAnimation(box, SpringAnimation.SCALE_Y, 0.5f);

//        velocityTracker = VelocityTracker.obtain();
        findViewById(R.id.root).setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        downX = event.getX();
//                        downY = event.getY();
                        animX.getSpring().setStiffness(getStiffness());
                        animX.getSpring().setDampingRatio(getDamping());
                        animX.getSpring().setFinalPosition(0.5f);

                        animY.getSpring().setStiffness(getStiffness());
                        animY.getSpring().setDampingRatio(getDamping());
                        animY.getSpring().setFinalPosition(0.5f);

                        animX.start();
                        animY.start();

//                        velocityTracker.addMovement(event);
                        return true;
                    case MotionEvent.ACTION_MOVE:
//                        box.setTranslationX(event.getX() - downX);
//                        box.setTranslationY(event.getY() - downY);
//                        velocityTracker.addMovement(event);
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
//                        velocityTracker.computeCurrentVelocity(1000);
//                        if (box.getTranslationX() != 0) {
//                            SpringAnimation animX = new SpringAnimation(box, SpringAnimation.TRANSLATION_X, 0.5f);
//                            animX.getSpring().setStiffness(getStiffness());
//                            animX.getSpring().setDampingRatio(getDamping());
//                           animX.setStartVelocity(velocityTracker.getXVelocity());
//                            animX.start();
//                        }
//                        if (box.getTranslationY() != 0) {
//                            SpringAnimation animY = new SpringAnimation(box, SpringAnimation.TRANSLATION_Y, 0.5f);
//                            animY.getSpring().setStiffness(getStiffness());
//                            animY.getSpring().setDampingRatio(getDamping());
//                            animY.setStartVelocity(velocityTracker.getYVelocity());
//                            animY.start();
//                        }
//                        velocityTracker.clear();
                        animX.getSpring().setFinalPosition(1);
                        animY.getSpring().setFinalPosition(1);

                        animX.start();
                        animY.start();
                        return true;
                }
                return false;
            }
        });
    }
//    setStiffness(float stiffness)方法设置弹性的生硬度，stiffness值越小
// ，弹簧越容易摆动，摆动的时间越长，反之摆动时间越短，注意它体验出来的最明显的特征是摆动时间这个概念
    private float getStiffness() {
        return Math.max(stiffness.getProgress(), 1f);
    }
//    setDampingRatio(float dampingRatio)方法设置弹性阻尼，dampingRatio越大，
// 摆动次数越少，当到1的时候完全不摆动，注意它体验出来的最明显的特征是摆动次数这个概念
    private float getDamping() {
        return damping.getProgress() / 100f;
    }
}
