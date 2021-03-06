package com.example.crazycat.viewapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.crazycat.viewapp.R;
import com.example.crazycat.viewapp.animation.Rotate3dAnimation;

public class Rool3DActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rool3_d);
        ImageView view = (ImageView) findViewById(R.id.pictrue);
        assert view != null;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 计算中心点（这里是使用view的中心作为旋转的中心点）
                final float centerX = v.getWidth() / 2.0f;
                final float centerY = v.getHeight() / 2.0f;

                //括号内参数分别为（上下文，开始角度，结束角度，x轴中心点，y轴中心点，深度，是否扭曲）
                final Rotate3dAnimation rotation = new Rotate3dAnimation(Rool3DActivity.this, 0, 180, centerX, centerY, 0f, true);

                rotation.setDuration(3000);                         //设置动画时长
                rotation.setFillAfter(true);                        //保持旋转后效果
                rotation.setInterpolator(new LinearInterpolator());	//设置插值器
                v.startAnimation(rotation);
            }
        });
    }
}
