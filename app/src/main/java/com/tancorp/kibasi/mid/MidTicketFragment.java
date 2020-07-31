package com.tancorp.kibasi.mid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.adapters.MTicketSelectorAdapter;
import com.tancorp.kibasi.managers.models.MTicketSelector;
import com.tancorp.kibasi.mid.qrcode.ScannerFragment;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MidTicketFragment extends Fragment
{
    public static ArrayList<MTicketSelector> _tickets;
    public static MTicketSelectorAdapter _adapter;
    private RecyclerView _recyclerView;
    private Button _scannerButton;
    private FrameLayout _frameLayout;
    private TextView _ticketScanned;
    private int _ticketNumber;

    public MidTicketFragment()
    {
        //Required
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_mid_ticket, container, false);

        _recyclerView = _view.findViewById(R.id.mid_ticket_recyclerview);
        _scannerButton = _view.findViewById(R.id.mid_ticket_verifier_button);
        _ticketScanned = _view.findViewById(R.id.mid_ticket_number);
        _frameLayout = Objects.requireNonNull(getActivity()).findViewById(R.id.mid_main_fragment_container);

        initRecyclerview();
        setupScanner();


        return _view;
    }


    private void setupScanner()
    {
        _scannerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ScannerFragment _fragment = new ScannerFragment();
                openFragment(_fragment);
            }
        });
    }

    private void openFragment(Fragment fragment)
    {
        FragmentTransaction _transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        _transaction.replace(_frameLayout.getId(), fragment);
        _transaction.addToBackStack(null);
        _transaction.commit();
    }

    @SuppressLint("SetTextI18n")
    private void initRecyclerview()
    {
        dataList();
        _adapter = new MTicketSelectorAdapter(_tickets);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _recyclerView.setAdapter(_adapter);

        _ticketNumber = _tickets.size();
        _ticketScanned.setText("" + _ticketNumber);

    }

    private void dataList()
    {
        _tickets = new ArrayList<>();
        _tickets.add(new MTicketSelector(R.drawable.onboard3, "Fretcher Hamadi Juma", "12wej2o56k", "R4"));
        _tickets.add(new MTicketSelector(R.drawable.onboard3, "Fretcher Hamadi Juma", "12wej2o56k", "R4"));

    }


}