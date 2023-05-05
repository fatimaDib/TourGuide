package com.example.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

//to show the images in slides
public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    int images[]={
            R.drawable.leb1,
            R.drawable.leb9,
            R.drawable.leb3,
            R.drawable.leb6,

    };
    int headings[]={
            R.string.titleIntro,
            R.string.titleIntro,
            R.string.titleIntro,
            R.string.titleIntro,
    };
    int subheading[]={
            R.string.name,
            R.string.name,
            R.string.name,
            R.string.name,
    };
    //on which activity to show this data
    public SliderAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //request from system to use the layout from context
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        //we have design inside view
        View view=layoutInflater.inflate(R.layout.slides_layout,container,false);

        //Hooks
        ImageView imageView=view.findViewById(R.id.slider_image);
        TextView texthView=view.findViewById(R.id.Slider_heading);
        TextView textshView=view.findViewById(R.id.Slider_subheading);

        imageView.setImageResource(images[position]);
        texthView.setText(headings[position]);
        textshView.setText(subheading[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
