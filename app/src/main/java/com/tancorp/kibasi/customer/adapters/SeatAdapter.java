package com.tancorp.kibasi.customer.adapters;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tancorp.kibasi.customer.CGridItemView;
import com.tancorp.kibasi.customer.models.Seat;

import java.util.ArrayList;



public class SeatAdapter extends BaseAdapter
{
    public ArrayList<Seat> _seats;
    public ArrayList<String> _selectedSeatsPosition;
    private String _gridId;

    public SeatAdapter(ArrayList<Seat> seats, String gridViewId)
    {
        _seats = seats;
        _gridId = gridViewId;
        _selectedSeatsPosition = new ArrayList<>();
    }

    @Override
    public int getCount()
    {
        return _seats.size();
    }

    @Override
    public Object getItem(int position)
    {
        return _seats.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        CGridItemView customView;

        if(convertView == null)
        {
            customView = new CGridItemView(parent.getContext());
        }
        else
        {

            customView = (CGridItemView) convertView;
        }

        customView.display(_seats.get(position).getSeatIcon(), _seats.get(position).getSeatIdText(), _selectedSeatsPosition.contains(_gridId + position));

        return customView;
    }
}
