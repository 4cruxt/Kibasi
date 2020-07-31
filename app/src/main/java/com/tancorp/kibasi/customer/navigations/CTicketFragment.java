package com.tancorp.kibasi.customer.navigations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.customer.adapters.TicketAdapter;
import com.tancorp.kibasi.customer.models.Ticket;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CTicketFragment extends Fragment
{
    public static final int TICKET_FRAGMENT_ID = 1;

    private ArrayList<Ticket> _paidTicketItem;
    private ArrayList<Ticket> _unpaidTicketItem;
    private RecyclerView _ticketRecyclerview;
    private RecyclerView _unpaidTicketRecyclerview;
    private TicketAdapter _unpaidTicketAdapter;
    private TicketAdapter _paidTicketAdapter;
    private FirebaseUser _currentUser;
    private FirebaseFirestore _firestore;

    public CTicketFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View _view = inflater.inflate(R.layout.fragment_ticket, container, false);
        _ticketRecyclerview = _view.findViewById(R.id.ticket_recyclerview);
        _unpaidTicketRecyclerview = _view.findViewById(R.id.unpaid_ticket_recyclerview);

        FirebaseAuth _auth = FirebaseAuth.getInstance();
        _currentUser = _auth.getCurrentUser();

        _firestore = FirebaseFirestore.getInstance();

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

        _unpaidTicketItem = new ArrayList<>();
        _unpaidTicketItem.add(new Ticket(R.drawable.onboard3, R.drawable.ic_unpaid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"));


        _paidTicketItem = new ArrayList<>();
        _paidTicketItem.add(new Ticket(R.drawable.onboard3, R.drawable.ic_paid_dot_24dp, "Shabiby Bus Service", "BCZ 2901", "L8", "17/8/2020"));

    }
}
