package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    TextView prev,next,skip;
    Button letstarted,nextt,previous,skipping;
    int currPos;
    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        //Hooks
        viewPager=findViewById(R.id.slider);
        dotsLayout=findViewById(R.id.dots);
        letstarted=findViewById(R.id.getstartedbtn);
        prev=findViewById(R.id.prevtxt);
        next=findViewById(R.id.nexttxt);
        skip=findViewById(R.id.skiptxt);
        nextt=findViewById(R.id.next);
        previous=findViewById(R.id.prev);
        skipping=findViewById(R.id.skipp);

        //call adapter
        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        adddots(0);
        viewPager.addOnPageChangeListener(changeListener);

    }
    public void next(View view){
        viewPager.setCurrentItem(currPos+1);
    }

    public void back(View view){
        viewPager.setCurrentItem(currPos-1);
    }


    public void skip(View view){
        startActivity(new Intent(getApplicationContext(),HomePage.class));
        finish();
    }

    public void logIn(View view){
        startActivity(new Intent(getApplicationContext(),LoginPage.class));
        finish();
    }

    private void adddots(int position){
        dots=new TextView[4];
        dotsLayout.removeAllViews();
        for(int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.dotsColor));
        }
    }
    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            adddots(position);
            currPos=position;
            if(position==0){
                skipping.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
                nextt.setVisibility(View.VISIBLE);
                prev.setVisibility(View.INVISIBLE);
                previous.setVisibility(View.INVISIBLE);
                skip.setVisibility(View.INVISIBLE);
                letstarted.setVisibility(View.INVISIBLE);
                
            } else if (position==1) {
                prev.setVisibility(View.VISIBLE);
                previous.setVisibility(View.VISIBLE);
                skip.setVisibility(View.INVISIBLE);
                skipping.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
                nextt.setVisibility(View.VISIBLE);
                letstarted.setVisibility(View.INVISIBLE);

            } else if (position==2) {
                prev.setVisibility(View.VISIBLE);
                previous.setVisibility(View.VISIBLE);
                skip.setVisibility(View.INVISIBLE);
                skipping.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
                nextt.setVisibility(View.VISIBLE);
                letstarted.setVisibility(View.INVISIBLE);

            }else {
                prev.setVisibility(View.VISIBLE);
                previous.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);
                nextt.setVisibility(View.INVISIBLE);
                skip.setVisibility(View.VISIBLE);
                skipping.setVisibility(View.VISIBLE);
                anim= AnimationUtils.loadAnimation(IntroActivity.this,R.anim.bottom_animation);
                letstarted.setAnimation(anim);
                letstarted.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}