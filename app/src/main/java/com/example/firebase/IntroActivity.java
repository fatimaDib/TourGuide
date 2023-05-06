package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class IntroActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dots;
    SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //Hooks
        viewPager=findViewById(R.id.slider);
        dots=findViewById(R.id.dots);

        //call adapter
        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

    }

    private void dots(){

    }
}