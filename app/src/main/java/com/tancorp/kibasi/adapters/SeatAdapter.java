package com.tancorp.kibasi.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.tancorp.kibasi.R;
import com.tancorp.kibasi.models.Seat;

public class SeatAdapter extends BaseAdapter
{
    private Seat[] _seats;

    public SeatAdapter(Seat[] seats)
    {
        _seats = seats;
    }

    @Override
    public int getCount()
    {
        return _seats.length;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat_item, null);

        ImageView _seatIcon = convertView.findViewById(R.id.seat_icon);
        _seatIcon.setImageResource(_seats[position].getSeatIcon());

        return convertView;
    }
}
