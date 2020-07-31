package com.tancorp.kibasi.managers.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.adapters.MTicketAdapter;
import com.tancorp.kibasi.managers.models.MTicket;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MPBusPagerFragment extends Fragment
{
    private RecyclerView _recyclerView;
    private ArrayList<MTicket> _busList;
    private FirebaseUser _currentUser;
    private FirebaseFirestore _firestore;
    private MTicketAdapter _adapter;
    private TextView _emptyText;
    private ProgressBar _progressBar;

    public MPBusPagerFragment()
    {
        //Required
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_m_p_bus_pager, container, false);

        FirebaseAuth _firebaseAuth = FirebaseAuth.getInstance();
        _currentUser = _firebaseAuth.getCurrentUser();
        _firestore = FirebaseFirestore.getInstance();

        _recyclerView = _view.findViewById(R.id.m_p_bus_pager_recylerview);
        _emptyText = _view.findViewById(R.id.m_p_bus_empty_text);
        _progressBar = _view.findViewById(R.id.m_p_bus_pager_progress_bar);

        initRecyclerview();

        return _view;
    }

    public void initRecyclerview()
    {
        dataList();
        _adapter = new MTicketAdapter(_busList, this);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _recyclerView.setAdapter(_adapter);
    }

    private void dataList()
    {
        _busList = new ArrayList<>();

        _firestore.collection("BUSES").whereEqualTo("bus_owner", _currentUser.getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    _progressBar.setVisibility(View.INVISIBLE);
                    _recyclerView.setVisibility(View.VISIBLE);

                    for(QueryDocumentSnapshot _documentSnapshot : Objects.requireNonNull(task.getResult()))
                    {
                        String from = Objects.requireNonNull(_documentSnapshot.get("bus_from_region")).toString();
                        String to = Objects.requireNonNull(_documentSnapshot.get("bus_to_region")).toString();

                        String fromRegion = from.substring(0, 1).toUpperCase() + from.substring(1);
                        String toRegion = to.substring(0, 1).toUpperCase() + to.substring(1);

                        String price = Objects.requireNonNull(_documentSnapshot.get("bus_seat_per_ticket")).toString() + "/=";

                        currencyFormatter(price);

                        _busList.add(new MTicket(R.drawable.onboard3, Objects.requireNonNull(_documentSnapshot.get("bus_name")).toString(), Objects.requireNonNull(_documentSnapshot.get("bus_plate_number")).toString(), fromRegion, toRegion, price, Objects.requireNonNull(_documentSnapshot.get("bus_seat_number")).toString()));
                    }

                    if(_busList.size() == 0)
                    {
                        _recyclerView.setVisibility(View.INVISIBLE);
                        _emptyText.setVisibility(View.VISIBLE);
                    }

                    _adapter.notifyDataSetChanged();

                }
                else
                {
                    FancyToast.makeText(getContext(), "" + Objects.requireNonNull(task.getException()).getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                }
            }
        });


    }

    private void currencyFormatter(String price)
    {
        //todo: Create a currency reader from a string.
    }

}