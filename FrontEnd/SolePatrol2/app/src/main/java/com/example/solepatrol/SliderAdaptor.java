package com.example.solepatrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdaptor extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdaptor(Context context)
    {
        this.context = context;
    }

    public int[] slider_images = {
            R.drawable.map,
            R.drawable.analysis,
            R.drawable.assistance,
            R.drawable.gdpr
    };

    public String[] slider_headings = {
            "NAVIGATION",
            "DATA-DRIVEN",
            "SUPPORT",
            "GDPR"
    };

    public String[] slider_descs = {
            "Quick and precise routing when you feel lost and unsafe",
            "Real-time mapping based on crime, lighting and traffic data",
            "Ability to call police services & other helplines when in danger",
            "When you use our app, we collect and store data about you. This includes your chosen username, password, emergency contact email and phone number. All your personal information is encrypted and stored securely in our database. We never sell or pass your information on to any external party. We will delete your data after 5 years of inactivity. If you need to change or request removal of the data we store, please contact SolePatrol@gmail.com. By clicking next you agree that you are over the age of 16 and give permission for your data to be stored and processed by us."
    };

    @Override
    public int getCount() {
        return slider_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        slideImageView.setImageResource(slider_images[position]);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_description);


        slideHeading.setText(slider_headings[position]);
        slideDescription.setText(slider_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }

}
