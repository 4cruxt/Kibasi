package com.tancorp.kibasi.adapters;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tancorp.kibasi.GridItemView;
import com.tancorp.kibasi.models.Seat;

import java.util.ArrayList;



public class SeatAdapter extends BaseAdapter
{
    public Seat[] _seats;
    public ArrayList<String> _selectedSeatsPosition;
    private String _gridId;

    public SeatAdapter(Seat[] seats, String gridViewId)
    {
        _seats = seats;
        _gridId = gridViewId;
        _selectedSeatsPosition = new ArrayList<>();
    }

    @Override
    public int getCount()
    {
        return _seats.length;
    }

    @Override
    public Object getItem(int position)
    {
        return _seats[position];
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

        GridItemView customView;

        if(convertView == null)
        {
            customView = new GridItemView(parent.getContext());
        }
        else
        {

            customView = (GridItemView) convertView;
        }

        customView.display(_seats[position].getSeatIcon(), _seats[position].getSeatIdText(), _selectedSeatsPosition.contains(_gridId + position));

        return customView;
    }
}
