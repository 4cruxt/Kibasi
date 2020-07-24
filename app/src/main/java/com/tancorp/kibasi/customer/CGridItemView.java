package com.tancorp.kibasi.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.tancorp.kibasi.R;

public class CGridItemView extends ConstraintLayout
{
    private ImageView _seatIcon;
    private TextView _seatIdText;

    public CGridItemView(Context context)
    {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.seat_item, this);
        _seatIcon = getRootView().findViewById(R.id.seat_icon);
        _seatIdText = getRootView().findViewById(R.id.seat_id_text);
    }

    public void display(int icon, String seatId, boolean isSelected)
    {
        _seatIcon.setImageResource(icon);
        _seatIdText.setText(seatId);
        display(isSelected);
    }

    public void display(boolean isSelected)
    {
        _seatIcon.setImageResource(isSelected ? R.drawable.selected_seat : R.drawable.available_seat);
    }

}
