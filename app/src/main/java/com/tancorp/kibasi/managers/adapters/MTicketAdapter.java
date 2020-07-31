package com.tancorp.kibasi.managers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.MSelectorTicketFragment;
import com.tancorp.kibasi.managers.models.MTicket;

import java.util.ArrayList;
import java.util.Objects;


public class MTicketAdapter extends RecyclerView.Adapter<MTicketAdapter.ViewHolder>
{
    private ArrayList<MTicket> _ticketsList;
    private Fragment _fragment;

    public MTicketAdapter(ArrayList<MTicket> ticketsList, Fragment fragment)
    {
        _ticketsList = ticketsList;
        _fragment = fragment;
    }

    @NonNull
    @Override
    public MTicketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater _inflater = LayoutInflater.from(parent.getContext());
        View _view = _inflater.inflate(R.layout.m_ticket_item, parent, false);

        return new ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull MTicketAdapter.ViewHolder holder, int position)
    {
        holder._busPhoto.setImageResource(_ticketsList.get(position).getBusPhoto());
        holder._busName.setText(_ticketsList.get(position).getBusName());
        holder._busNumber.setText(_ticketsList.get(position).getBusNumber());
        holder._fromRegion.setText(_ticketsList.get(position).getFromRegion());
        holder._toRegion.setText(_ticketsList.get(position).getToRegion());
        holder._ticketPrice.setText(_ticketsList.get(position).getTicketPrice());
        holder._busSeat.setText(_ticketsList.get(position).getBusSeat());

        holder._container.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MSelectorTicketFragment _fragmentSelectorTicket = new MSelectorTicketFragment();
                FragmentTransaction transaction = Objects.requireNonNull(_fragment.getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_out_from_left);
                transaction.replace(R.id.m_fragment_container, _fragmentSelectorTicket);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }


    @Override
    public int getItemCount()
    {
        return _ticketsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView _busPhoto;
        private TextView _busName;
        private TextView _busNumber;
        private TextView _fromRegion;
        private TextView _toRegion;
        private TextView _ticketPrice;
        private TextView _busSeat;
        private ConstraintLayout _container;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            _busPhoto = itemView.findViewById(R.id.m_ticket_bus_image);
            _busName = itemView.findViewById(R.id.m_ticket_bus_name);
            _busNumber = itemView.findViewById(R.id.m_ticket_bus_number);
            _fromRegion = itemView.findViewById(R.id.m_ticket_from_region);
            _toRegion = itemView.findViewById(R.id.m_ticket_to_region);
            _ticketPrice = itemView.findViewById(R.id.m_ticket_price);
            _busSeat = itemView.findViewById(R.id.m_ticket_seat_number);
            _container = itemView.findViewById(R.id.m_ticket_container);
        }
    }
}
