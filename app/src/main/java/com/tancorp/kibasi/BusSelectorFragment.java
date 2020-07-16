package com.tancorp.kibasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.adapters.BusAdapter;
import com.tancorp.kibasi.models.Bus;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusSelectorFragment extends Fragment
{

    private RecyclerView _busRecyclerview;
    private Bus[] _busItem;
    private BusAdapter _busAdapter;

    public BusSelectorFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_bus_selector, container, false);
        _busRecyclerview = _view.findViewById(R.id.buses_selector_recyclerview);

        initRecyclerview();
        return _view;
    }

    private void initRecyclerview()
    {
        dataList();
        _busAdapter = new BusAdapter(this.getActivity(), _busItem);
        _busRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        _busRecyclerview.setAdapter(_busAdapter);
        _busAdapter.notifyDataSetChanged();

    }

    private void dataList()
    {
        String _pricePlaceholder = "Tshs.";

        _busItem = new Bus[]{new Bus("Shabiby Bus Service", "7.30", _pricePlaceholder + " 32,000", "02:00", "9:30", 0), new Bus("Expo Bus Service", "12", _pricePlaceholder + " 23,000", "02:00", "9:30", 0), new Bus("Red Bus Service", "32.20", _pricePlaceholder + " 45,000", "02:00", "9:30", 0), new Bus("Star Bus", "34.40", _pricePlaceholder + " 54,000", "02:00", "9:30", 0), new Bus("Travelo Service", "36.06", _pricePlaceholder + " 33,000", "02:00", "9:30", 0), new Bus("Tashirif Service", "78", _pricePlaceholder + " 55,000", "02:00", "9:30", 0), new Bus("Torinto Bus", "7", _pricePlaceholder + " 54,000", "02:00", "9:30", 0), new Bus("Ngarika Bus", "2", _pricePlaceholder + "32,000", "02:00", "9:30", 0), new Bus("Dar Lux Service", "23", _pricePlaceholder + " 87,000", "02:00", "9:30", 0), new Bus("Abood Bus", "7.30", _pricePlaceholder + " 21,000", "02:00", "9:30", 0),};
    }

}
