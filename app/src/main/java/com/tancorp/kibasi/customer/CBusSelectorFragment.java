package com.tancorp.kibasi.customer;

import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.customer.adapters.BusAdapter;
import com.tancorp.kibasi.customer.models.Bus;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class CBusSelectorFragment extends Fragment
{

    private RecyclerView _busRecyclerview;
    private ArrayList<Bus> _busItem;
    private BusAdapter _busAdapter;
    private ArrayList<String> _searching_data;
    private String _fromRegion;
    private String _toRegion;
    private String _dateTravel;
    private TextView _fromRegionText;
    private TextView _toRegionText;
    private TextView _errorText;
    private TextView _dateTravelText;
    private FirebaseFirestore _firestore;
    private ProgressBar _progressBar;


    public CBusSelectorFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_bus_selector, container, false);

        _busRecyclerview = _view.findViewById(R.id.buses_selector_recyclerview);
        _fromRegionText = _view.findViewById(R.id.bss_from_region_text);
        _toRegionText = _view.findViewById(R.id.bss_to_region_text);
        _dateTravelText = _view.findViewById(R.id.date_bus_departure_text);
        _progressBar = _view.findViewById(R.id.bus_selector_progressbar);
        _errorText = _view.findViewById(R.id.bss_error_text);

        _firestore = FirebaseFirestore.getInstance();

        queryData();
        initRecyclerview();
        return _view;
    }

    @SuppressWarnings("ConstantConditions")
    private void queryData()
    {
        _searching_data = getArguments().getStringArrayList("searching_results");

        _fromRegion = _searching_data.get(0);
        _toRegion = _searching_data.get(1);
        _dateTravel = _searching_data.get(2);

        _fromRegionText.setText(_fromRegion);
        _toRegionText.setText(_toRegion);
        _dateTravelText.setText(_dateTravel);

    }

    private void initRecyclerview()
    {
        dataList();
        _busAdapter = new BusAdapter(this.getActivity(), _busItem, _searching_data);
        _busRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        _busRecyclerview.setAdapter(_busAdapter);

    }


    private void dataList()
    {
        String _pricePlaceholder = "Tshs.";

        _busItem = new ArrayList<>();
//        _busItem.add(new Bus(bus name, total time taken, price, from time, to time, bus image));

        _firestore.collection("BUSES").whereEqualTo("bus_from_region", _fromRegion.toLowerCase()).whereEqualTo("bus_to_region", _toRegion.toLowerCase()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    _progressBar.setVisibility(View.INVISIBLE);
                    _busRecyclerview.setVisibility(View.VISIBLE);

                    for(QueryDocumentSnapshot _documentSnapshot : Objects.requireNonNull(task.getResult()))
                    {
                        Log.i("LIST OF BUS", Objects.requireNonNull(_documentSnapshot.get("bus_name")).toString());

                        _busItem.add(new Bus(Objects.requireNonNull(_documentSnapshot.get("bus_name")).toString(), "10", Objects.requireNonNull(_documentSnapshot.get("bus_seat_per_ticket")).toString() + "/=", Objects.requireNonNull(_documentSnapshot.get("bus_from_time")).toString(), Objects.requireNonNull(_documentSnapshot.get("bus_to_time")).toString(), Objects.requireNonNull(_documentSnapshot.get("bus_plate_number")).toString(), 0, Integer.parseInt(Objects.requireNonNull(_documentSnapshot.get("bus_seat_number")).toString())));
                    }

                    if(_busItem.size() == 0)
                    {
                        _busRecyclerview.setVisibility(View.INVISIBLE);
                        _errorText.setVisibility(View.VISIBLE);
                    }

                    _busAdapter.notifyDataSetChanged();
                }
                else
                {
                    Log.i("NO BUSES", Objects.requireNonNull(Objects.requireNonNull(task.getException()).getMessage()));
                }

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Log.i("FIREBASE ERROR", Objects.requireNonNull(e.getMessage()));
            }
        });


    }

}
