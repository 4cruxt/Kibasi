package com.tancorp.kibasi.navigations;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.R;
import com.tancorp.kibasi.adapters.TicketAdapter;
import com.tancorp.kibasi.models.Ticket;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketFragment extends Fragment
{

    private Ticket[] _ticketItem;
    private RecyclerView _ticketRecyclerview;
    private TicketAdapter _ticketAdapter;

    public TicketFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //Create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.TicketTheme);
        //Clone the inflater using the ContextThemeWrapper
        LayoutInflater _localInflater = inflater.cloneInContext(contextThemeWrapper);
        // Inflate the layout for this fragment
        View _view = _localInflater.inflate(R.layout.fragment_ticket, container, false);
        _ticketRecyclerview = _view.findViewById(R.id.ticket_recyclerview);

        initRecyclerview();

        return _view;
    }

    private void initRecyclerview()
    {
        ticketDataList();
        _ticketAdapter = new TicketAdapter(_ticketItem);
        _ticketRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        _ticketRecyclerview.setAdapter(_ticketAdapter);
        _ticketAdapter.notifyDataSetChanged();

    }

    private void ticketDataList()
    {
        _ticketItem = new Ticket[]{new Ticket(0, "Shabiby Service", "7/7/2020"), new Ticket(0, "Expo Service", "8/5/2020"), new Ticket(0, "Star Bus", "2/2/2019"),};
    }
}
