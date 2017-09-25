package com.example.crazycat.viewapp.activity;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.crazycat.viewapp.R;

public class SVGActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        ImageView imageView = (ImageView) findViewById(R.id.ivx);
        final Drawable drawable = imageView.getDrawable();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawable instanceof Animatable){
                    ((Animatable) drawable).start();
                }
            }
        });

    }
}
