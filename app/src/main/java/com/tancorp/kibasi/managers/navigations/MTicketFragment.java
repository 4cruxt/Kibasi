package com.tancorp.kibasi.managers.navigations;

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

import static com.tancorp.kibasi.managers.MMainActivity._navigationView;

/**
 * A simple {@link Fragment} subclass.
 */

public class MTicketFragment extends Fragment
{
    public static final int MTICKET_FRAGMENT_ID = 1;

    private RecyclerView _recyclerView;
    private ArrayList<MTicket> _mTickets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View _view = inflater.inflate(R.layout.fragment_m_ticket, container, false);
        _navigationView.setVisibility(View.VISIBLE);


        _recyclerView = _view.findViewById(R.id.m_ticket_recyclerview);

        initRecyclerview();

        return _view;
    }

    private void initRecyclerview()
    {
        dataList();
        MTicketAdapter _adapter = new MTicketAdapter(_mTickets, MTicketFragment.this);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _recyclerView.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();
    }

    private void dataList()
    {
        _mTickets = new ArrayList<>();

        for(int i = 1; i <= 10; i++)
        {
            _mTickets.add(new MTicket(R.drawable.onboard3, "Nganga Bus Service", "NJD 2134", "Iringa", "Dar Es Salaam", "43000/=", "54" + i));
        }

    }

}