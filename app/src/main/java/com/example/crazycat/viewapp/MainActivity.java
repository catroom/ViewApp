package com.example.crazycat.viewapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void goRadarView(View view){
        startActivity(new Intent(this,RadarviewActivity.class));
    }
    public void goPieView(View view){
        startActivity(new Intent(this,PieViewActivitty.class));
    }
    public void Bezier(View view){
        startActivity(new Intent(this,BezierActivitty.class));
    }
    public void Bezier3(View view){
        startActivity(new Intent(this,Bezier3Activity.class));
    }
    public void rool3D(View view){
        startActivity(new Intent(this,Rool3DActivity.class));
    }
    public void rebound(View view){
        startActivity(new Intent(this,ReboundActivity.class));
    }
    public void Spring(View view){
        startActivity(new Intent(this,SpringActivity.class));
    }
}
