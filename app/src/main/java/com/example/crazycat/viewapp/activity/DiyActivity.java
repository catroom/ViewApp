package com.example.crazycat.viewapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.crazycat.viewapp.R;

public class DiyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy);
    }
    public void volume(View view){
        startActivity(new Intent(this,VolumeActivity.class));
    }
}
