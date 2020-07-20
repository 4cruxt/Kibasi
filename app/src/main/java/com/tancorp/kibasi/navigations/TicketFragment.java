package com.tancorp.kibasi.navigations;

import android.os.Bundle;
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
    public static final int TICKET_FRAGMENT_ID = 1;

    private Ticket[] _paidTicketItem;
    private Ticket[] _unpaidTicketItem;
    private RecyclerView _ticketRecyclerview;
    private RecyclerView _unpaidTicketRecyclerview;
    private TicketAdapter _unpaidTicketAdapter;
    private TicketAdapter _paidTicketAdapter;

    public TicketFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View _view = inflater.inflate(R.layout.fragment_ticket, container, false);
        _ticketRecyclerview = _view.findViewById(R.id.ticket_recyclerview);
        _unpaidTicketRecyclerview = _view.findViewById(R.id.unpaid_ticket_recyclerview);

        enableRecyclerViewData(_unpaidTicketRecyclerview, _ticketRecyclerview);

        return _view;
    }

    private void enableRecyclerViewData(RecyclerView unpaidTicketRecyclerview, RecyclerView paidTicketRecyclerview)
    {
        initRecyclerview(unpaidTicketRecyclerview, paidTicketRecyclerview);

    }

    private void initRecyclerview(RecyclerView unpaidTicketRecyclerview, RecyclerView paidTicketRecyclerview)
    {
        ticketDataList();

        //unpaid ticket adapter initialization
        _unpaidTicketAdapter = new TicketAdapter(_unpaidTicketItem, false);
        unpaidTicketRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        unpaidTicketRecyclerview.setAdapter(_unpaidTicketAdapter);
        _unpaidTicketAdapter.notifyDataSetChanged();

        //paid tickeet adapter initialization
        _paidTicketAdapter = new TicketAdapter(_paidTicketItem, true);
        paidTicketRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        paidTicketRecyclerview.setAdapter(_paidTicketAdapter);
        _paidTicketAdapter.notifyDataSetChanged();

    }

    private void ticketDataList()
    {
        _unpaidTicketItem = new Ticket[]{new Ticket(R.drawable.onboard3, R.drawable.ic_unpaid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"), new Ticket(R.drawable.onboard3, R.drawable.ic_unpaid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"),

        };
        _paidTicketItem = new Ticket[]{new Ticket(R.drawable.onboard3, R.drawable.ic_paid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"), new Ticket(R.drawable.onboard3, R.drawable.ic_paid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"), new Ticket(R.drawable.onboard3, R.drawable.ic_paid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"), new Ticket(R.drawable.onboard3, R.drawable.ic_paid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"), new Ticket(R.drawable.onboard3, R.drawable.ic_paid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"), new Ticket(R.drawable.onboard3, R.drawable.ic_paid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"), new Ticket(R.drawable.onboard3, R.drawable.ic_paid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"), new Ticket(R.drawable.onboard3, R.drawable.ic_paid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"), new Ticket(R.drawable.onboard3, R.drawable.ic_paid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"), new Ticket(R.drawable.onboard3, R.drawable.ic_paid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"),};
    }
}
