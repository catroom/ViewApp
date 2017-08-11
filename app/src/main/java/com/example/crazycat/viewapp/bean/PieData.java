package com.example.crazycat.viewapp.bean;

import android.support.annotation.Nullable;

/**
 * Created by crazycat on 2017/6/20.
 */

public class PieData {

    private String name; //名字
    private float value;//数值
    private float percentage;//百分比

    private int color=0;//颜色
    private float angle=0;//角度

    public PieData(@Nullable String name, @Nullable float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
