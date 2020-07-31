package com.tancorp.kibasi.managers.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.adapters.MExploreVerifierAdapter;
import com.tancorp.kibasi.managers.models.MExploreVerifier;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class METicketPagerFragment extends Fragment
{

    private RecyclerView _recyclerView;
    private ArrayList<MExploreVerifier> _verifiers;
    private MExploreVerifierAdapter _adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_m_e_ticket_pager, container, false);

        _recyclerView = _view.findViewById(R.id.m_e_ticket_pager_recyclerview);

        initRecyclerview();

        return _view;
    }

    private void initRecyclerview()
    {
        dataList();
        _adapter = new MExploreVerifierAdapter(_verifiers);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _recyclerView.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();

    }

    private void dataList()
    {
        _verifiers = new ArrayList<>();

        for(int i = 1; i <= 19; i++)
        {
            _verifiers.add(new MExploreVerifier("Erick Mafole", "Juma Lokole", "dw2w24w422"));
        }

    }
}