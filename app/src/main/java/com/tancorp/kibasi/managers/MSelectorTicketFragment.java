package com.tancorp.kibasi.managers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.adapters.MTicketSelectorAdapter;
import com.tancorp.kibasi.managers.models.MTicketSelector;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MSelectorTicketFragment extends Fragment
{

    private RecyclerView _recyclerView;
    private ArrayList<MTicketSelector> _tickets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_m_passenger_ticket, container, false);

        _recyclerView = _view.findViewById(R.id.m_ticket_pss_recyclerview);

        initRecyclerview();

        return _view;
    }

    private void initRecyclerview()
    {
        dataList();
        MTicketSelectorAdapter _adapter = new MTicketSelectorAdapter(_tickets);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _recyclerView.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();
    }

    private void dataList()
    {
        _tickets = new ArrayList<>();

        for(int i = 1; i <= 20; i++)
        {
            _tickets.add(new MTicketSelector(R.drawable.onboard3, "Fretcher Hamadi Juma", "12wej2o56k", "R4"));
        }

    }
}