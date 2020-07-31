package com.tancorp.kibasi.managers.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.adapters.MTicketAdapter;
import com.tancorp.kibasi.managers.models.MTicket;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MEBusPagerFragment extends Fragment
{
    private RecyclerView _recyclerView;
    private ArrayList<MTicket> _tickets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_m_e_bus_pager, container, false);


        _recyclerView = _view.findViewById(R.id.m_e_bus_pager_recyclerview);


        initRecyclerview();
        return _view;
    }

    private void initRecyclerview()
    {
        dataList();
        MTicketAdapter _adapter = new MTicketAdapter(_tickets, this);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _recyclerView.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();

    }

    private void dataList()
    {
        _tickets = new ArrayList<>();

        for(int i = 1; i <= 43; i++)
        {
            _tickets.add(new MTicket(R.drawable.onboard3, "Nganga Bus Service", "NTJ 4321", "Arusha", "Kilimanjaro", "" + i, "64"));
        }

    }
}