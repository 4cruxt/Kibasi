package com.tancorp.kibasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookedTicketFragment extends Fragment
{

    public BookedTicketFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View _view = inflater.inflate(R.layout.booked_ticket, container, false);


        return _view;
    }
}
