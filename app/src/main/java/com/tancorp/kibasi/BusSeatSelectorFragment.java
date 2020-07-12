package com.tancorp.kibasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.tancorp.kibasi.adapters.SeatAdapter;
import com.tancorp.kibasi.models.Seat;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusSeatSelectorFragment extends Fragment
{

    private GridView _bssLeftGridview;
    private GridView _bssRightGridview;
    private Seat[] _seatItem;

    public BusSeatSelectorFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_bus_seat_selector, container, false);

        _bssLeftGridview = _view.findViewById(R.id.bss_left_gridview);
        _bssRightGridview = _view.findViewById(R.id.bss_right_gridview);

        enablingGridviewData(_bssLeftGridview);
        enablingGridviewData(_bssRightGridview);

        return _view;
    }

    private void enablingGridviewData(GridView view)
    {
        initGridview(view);
        clickedGridviewItem(view);
    }

    private void initGridview(GridView gridView)
    {
        dataList();
        SeatAdapter _seatAdapter = new SeatAdapter(_seatItem);
        gridView.setAdapter(_seatAdapter);
    }

    private void dataList()
    {
        _seatItem = new Seat[]{new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background), new Seat(R.drawable.ic_launcher_background)};
    }

    private void clickedGridviewItem(GridView _gridView)
    {
        _gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Seat _seat = _seatItem[position];
                Toast.makeText(getContext(), "seat id" + _seat, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
