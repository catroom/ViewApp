package com.example.crazycat.viewapp.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by crazycat on 2017/8/18.
 */

public class VolumeWaveView extends View {

    private  Paint mWhitePaint;
    private  Paint mLinearPaint;
    private  Paint mPurpleRectPaint;
    private  Paint mPinkRectPaint;
    private  Paint mRedRectPaint;
    private  Paint mBuleRectPaint;
    private  Paint mGreenPaint;
    private  Paint mRectPaint;

    private PathMeasure mPathMeasure;
    private float mAnimatorValue;
    private Path mDst;
    private float mLength;
    Path path;

    private float centerX,centerY;


    public VolumeWaveView(Context context) {
        this(context,null);

    }

    public VolumeWaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VolumeWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //外层蓝色矩形 30.176.246
        mRectPaint=new Paint();
        mRectPaint.setAntiAlias(true);
        mRectPaint.setStyle(Paint.Style.STROKE);//默认实心
        mRectPaint.setColor(Color.rgb(30,176,246));
        mRectPaint.setStrokeWidth(20);

        mWhitePaint=new Paint();
        mWhitePaint.setAntiAlias(true);
        mWhitePaint.setStyle(Paint.Style.STROKE);//默认实心
        mWhitePaint.setColor(Color.WHITE);
        mWhitePaint.setStrokeWidth(100);


        mLinearPaint=new Paint();
        Shader shader = new LinearGradient(0, 0, 100, 100, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        mLinearPaint.setShader(shader);
        mLinearPaint.setStrokeWidth(20);

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(5000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();



//        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
//                invalidate();
//            }
//        });
//
//        valueAnimator.setDuration(2000);
//        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        valueAnimator.start();
        //绿色小圆点 38,238,231
//        mGreenPaint=new Paint();
//        mGreenPaint.setAntiAlias(true);
//        mGreenPaint.setColor(Color.rgb(38,238,2321));
//
//        //蓝色圆角矩形
//        mBuleRectPaint=new Paint();
//        mBuleRectPaint.setAntiAlias(true);
//        mBuleRectPaint.setColor(Color.rgb(30,176,246));
//        mBuleRectPaint.setStrokeWidth(10);
//
//        //紫色圆角小矩形 153,116,252
//        mPurpleRectPaint=new Paint();
//        mPurpleRectPaint.setAntiAlias(true);
//        mPurpleRectPaint.setColor(Color.rgb(153,116,252));
//        mPurpleRectPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPurpleRectPaint.setStrokeWidth(10);
//
//        //粉红色小矩形 214，68，249
//        mPinkRectPaint=new Paint();
//        mPinkRectPaint.setAntiAlias(true);
//        mPinkRectPaint.setColor(Color.rgb(214,68,249));
//        mPinkRectPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPinkRectPaint.setStrokeWidth(10);
//
//        //中间红色小矩形252.42，196
//        mRectPaint=new Paint();
//        mRectPaint.setAntiAlias(true);
//        mRectPaint.setColor(Color.rgb(252,42,196));
//        mRectPaint.setStrokeWidth(10);
    }
//    tLength()	获取Path的长度
//    boolean	nextContour()	跳转到下一个轮廓
//    boolean	getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo)	截取片段
//    boolean	getPosTan(float distance, float[] pos, float[] tan)	获取指定长度的位置坐标及该点切线值
//    boolean	getMatrix(float distance, Matrix matrix, int flags)	获取指定长度的位置坐标及该点Matrixvoid
// setPath(Path path, boolean forceClosed)	关联一个Path
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        Log.e("onDraw","onDraw");
        Log.e("centerX",centerX+"");
        Log.e("centerY",centerY+"");
        RectF rectF=new RectF(centerX-400,centerY-400,centerX+400,centerY+400);
        path=new Path();
        path.addRoundRect(rectF,60,60, Path.Direction.CCW);
        canvas.drawPath(path,mRectPaint);
        canvas.drawLine(0,centerY,centerX*2,centerY,mWhitePaint);


        mPathMeasure=new PathMeasure();
        mPathMeasure.setPath(path, true);
        mLength=mPathMeasure.getLength();

        mDst = new Path();
        mDst.reset();
        mDst.lineTo(0,0);
        float stop = mLength * mAnimatorValue;
        float start = (float) (stop - 0.1*mLength);
        mPathMeasure.getSegment(start, stop, mDst, true);
        canvas.drawPath(mDst, mLinearPaint);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("onSizeChanged","onSizeChanged");
        centerX=w/2;
        centerY=h/2;
    }
}
