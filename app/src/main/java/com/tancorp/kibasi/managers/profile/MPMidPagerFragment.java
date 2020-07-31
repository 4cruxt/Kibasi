package com.tancorp.kibasi.managers.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.tancorp.kibasi.managers.adapters.MPMidAdapter;
import com.tancorp.kibasi.managers.models.MPMid;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MPMidPagerFragment extends Fragment
{

    private RecyclerView _recyclerView;
    private ArrayList<MPMid> _midsList;
    private FirebaseUser _currentUser;
    private FirebaseFirestore _firestore;
    private MPMidAdapter _adapter;
    private ProgressBar _progressBar;
    private TextView _emptyText;

    public MPMidPagerFragment()
    {
        //Required
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_m_p_mid_pager, container, false);

        FirebaseAuth _auth = FirebaseAuth.getInstance();
        _currentUser = _auth.getCurrentUser();
        _firestore = FirebaseFirestore.getInstance();

        _recyclerView = _view.findViewById(R.id.m_p_mid_pager_recyclerview);
        _progressBar = _view.findViewById(R.id.m_p_mid_pager_progress_bar);
        _emptyText = _view.findViewById(R.id.m_p_mid_pager_empty_text);

        initRecyclerview();

        return _view;
    }

    public void initRecyclerview()
    {
        dataList();
        _adapter = new MPMidAdapter(_midsList);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _recyclerView.setAdapter(_adapter);

    }

    private void dataList()
    {
        _midsList = new ArrayList<>();

        _firestore.collection("MIDS").whereEqualTo("bus_owner", _currentUser.getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
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
                        //midlist = new MPMid( mid_photo, mid_name, mid_phone_number, mid_bus_given)
                        _midsList.add(new MPMid(R.drawable.onboard3, Objects.requireNonNull(_documentSnapshot.get("mid_name")).toString(), Objects.requireNonNull(_documentSnapshot.get("mid_phone_number")).toString(), Objects.requireNonNull(_documentSnapshot.get("mid_bus_given")).toString()));

                    }

                    if(_midsList.size() == 0)
                    {
                        _recyclerView.setVisibility(View.INVISIBLE);
                        _emptyText.setVisibility(View.VISIBLE);
                    }

                    _adapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    FancyToast.makeText(getContext(), "" + task.getException().getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false);
                }
            }
        });


    }

}