package com.tancorp.kibasi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.tancorp.kibasi.R;

@SuppressWarnings("ALL")
public class SliderAdapter extends PagerAdapter
{
    //Arrays
    public int[] slider_images = {R.drawable.onboard2, R.drawable.onboard2, R.drawable.onboard2};
    public String[] slider_headings = {"TIKETI", "SAFARI", "USALAMA"};
    public String[] slider_descriptions = {"Kata tiketi yako chapu kwa haraka popote ulipo", "Uhuru wa kusafiri popote na basi lolote ulipendalo", "Usalama wa kupata tiketi bora zaidi bila usumbufu"};
    Context _context;
    LayoutInflater _layoutInflater;

    public SliderAdapter(Context context)
    {
        _context = context;
    }

    @Override
    public int getCount()
    {
        return slider_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {

        _layoutInflater = (LayoutInflater) _context.getSystemService(_context.LAYOUT_INFLATER_SERVICE);
        View _view = _layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView _sliderImage = _view.findViewById(R.id.slider_image);
        TextView _sliderHeadingText = _view.findViewById(R.id.slider_heading);
        TextView _sliderDescriptionText = _view.findViewById(R.id.slider_description);

        _sliderImage.setImageResource(slider_images[position]);
        _sliderHeadingText.setText(slider_headings[position]);
        _sliderDescriptionText.setText(slider_descriptions[position]);

        container.addView(_view);

        return _view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((ConstraintLayout) object);
    }
}
