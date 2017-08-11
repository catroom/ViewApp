package com.example.crazycat.viewapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.crazycat.viewapp.R;
import com.example.crazycat.viewapp.view.RadarView;

/**
 * Created by crazycat on 2017/7/6.
 */

public class RadarviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radarview);
        RadarView view= (RadarView) findViewById(R.id.view);
        String[] countries={"上海","北京","广州","深圳","珠海","中山"};
        view.setTitles(countries);
        view.setTextPaintColor(R.color.colorAccent);
        view.setValuePaintColor(R.color.colorAccent);
        view.setMainPaintColor(R.color.colorPrimary);
        view.setMaxValue(200);
    }
}
