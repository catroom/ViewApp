package com.example.crazycat.viewapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by crazycat on 2017/7/5.
 */

public class RadarView extends View {

    private int count = 6;
    private float angel = (float) (Math.PI * 2 / count);
    private float radius; //半径
    private int centerx;//中心x
    private int centery;//中心y
    private String[] titles = {"a", "b", "c", "d", "e", "f"};
    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20};//各维度分值
    private Paint mainPaint;                //雷达区画笔
    private Paint valuePaint;               //数据区画笔
    private Paint textPaint;                //文本画笔
    private String LOG_TAG = "雷达";
    private float maxValue=100;

    public RadarView(Context context) {
        super(context);
        init();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.e(LOG_TAG, "初始化");
        count = Math.min(data.length, titles.length);

        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.GRAY);
        mainPaint.setStyle(Paint.Style.STROKE);

        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint();
        textPaint.setTextSize(30);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.e(LOG_TAG, "onSizeChanged");
        radius = Math.min(h, w) / 2 * 0.8f;
        //中心坐标
        centerx = w / 2;
        centery = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        Log.e(LOG_TAG, "onDraw");
        drawPolygon(canvas);//多边形
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);//范围
    }


    /**
     * 绘制正多边形
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);//r是蜘蛛丝之间的间距
        for (int i = 0; i < count; i++) {//中心点不用绘制
            float curr = r * i;//当前半径
            path.reset();//重置path
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerx + curr, centery);//首先设置一个起点先右边起点
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerx + curr * Math.cos(angel * j));
                    float y = (float) (centery + curr * Math.sin(angel * j));
                    path.lineTo(x, y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 画直线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerx, centery);//每次都从中心点
            float x = (float) (centerx + radius * Math.cos(angel * i));//改变直线的，xy终点
            float y = (float) (centery + radius * Math.sin(angel * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 第一象限：（正+,+正），横纵坐标同号，记作xy>0
     * 第二象限：（负-,+正），横纵坐标异号，记作xy<0
     * 第三象限：（负-,-负），横纵坐标同号，记作xy>0
     * 第四象限：（正+,-负），横纵坐标异号，记作xy<0
     * 文字abc
     *
     * @param
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;//顶线和底线
        for (int i = 0; i < count; i++) {//文字的中间位置
            float x = (float) (centerx + (radius + fontHeight / 2) * Math.cos(angel * i));
            float y = (float) (centery + (radius + fontHeight / 2) * Math.sin(angel * i));
            if (angel * i >= 0 && angel * i <= Math.PI / 2) {//第四象限

                canvas.drawText(titles[i], x, y, textPaint);

            } else if (angel * i >= 3 * Math.PI / 2 && angel * i <= Math.PI * 2) {//3

                canvas.drawText(titles[i], x, y, textPaint);

            } else if (angel * i > Math.PI / 2 && angel * i <= Math.PI ) {//2

                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i], x - dis, y, textPaint);

            } else if (angel * i >= Math.PI && angel * i < 3 * Math.PI / 2) {//1

                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y, textPaint);
            }
        }
    }

    /**
     * 绘制区域
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < count; i++) {
            double percent=data[i]/maxValue;
            float x= (float) (centerx+radius*Math.cos(angel*i)*percent);
            float y = (float) (centery+radius*Math.sin(angel*i)*percent);
            if(i==0){
                path.moveTo(x, centery);
            }else{
                path.lineTo(x,y);
            }
            //绘制小圆点
            canvas.drawCircle(x,y,10,valuePaint);
        }
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path,valuePaint);
        valuePaint.setAlpha(127);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path,valuePaint);
    }

    //设置标题
    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    //设置数值
    public void setData(double[] data) {
        this.data = data;
    }


    public float getMaxValue() {
        return maxValue;
    }

    //设置最大数值
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    //设置蜘蛛网颜色
    public void setMainPaintColor(int color) {
        mainPaint.setColor(color);
        Log.e(LOG_TAG,"设置蜘蛛网颜色");
    }

    //设置标题颜色
    public void setTextPaintColor(int color) {
        textPaint.setColor(color);
        Log.e(LOG_TAG,"设置标题颜色");

    }

    //设置覆盖局域颜色
    public void setValuePaintColor(int color) {
        valuePaint.setColor(color);
        Log.e(LOG_TAG,"覆盖局域颜色");

    }

}
