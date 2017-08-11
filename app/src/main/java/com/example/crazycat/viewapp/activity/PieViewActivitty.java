package com.example.crazycat.viewapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.crazycat.viewapp.bean.PieData;
import com.example.crazycat.viewapp.R;
import com.example.crazycat.viewapp.view.PieView;

import java.util.ArrayList;

/**
 * Created by crazycat on 2017/7/6.
 */

public class PieViewActivitty extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pieview);
        PieView view = new PieView(this);
        setContentView(view);

        ArrayList<PieData> datas = new ArrayList<>();
        PieData pieData = new PieData("sloop", 60);
        PieData pieData2 = new PieData("sloop", 30);
        PieData pieData3 = new PieData("sloop", 40);
        PieData pieData4 = new PieData("sloop", 20);
        PieData pieData5 = new PieData("sloop", 20);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        view.setData(datas);
    }
}
