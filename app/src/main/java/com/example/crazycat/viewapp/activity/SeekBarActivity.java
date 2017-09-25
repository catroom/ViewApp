package com.example.crazycat.viewapp.activity;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.example.crazycat.viewapp.R;

public class SeekBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekbar);
//        LinearGradient test = new LinearGradient(0.f, 0.f, 300.f, 0.0f,
//
//                new int[] { 0xFFFF00, 0xFF0000FF, 0xFF00FF00, 0xFF00FFFF,
//                        0xFFFF0000, 0xFFFF00FF, 0xFFFFFF00, 0xFFFFFFFF},
//                null, Shader.TileMode.CLAMP);
//        ShapeDrawable shape = new ShapeDrawable(new RectShape());
//        shape.getPaint().setShader(test);
//
//        seekBarFont.setProgressDrawable( (Drawable)shape );
    }
}
