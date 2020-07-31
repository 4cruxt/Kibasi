package com.tancorp.kibasi.managers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.models.MTicketSelector;

import java.util.ArrayList;

public class MTicketSelectorAdapter extends RecyclerView.Adapter<MTicketSelectorAdapter.ViewHolder>
{
    private ArrayList<MTicketSelector> _mTicketsListSelector;

    public MTicketSelectorAdapter(ArrayList<MTicketSelector> mTicketsListSelector)
    {
        _mTicketsListSelector = mTicketsListSelector;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater _inflater = LayoutInflater.from(parent.getContext());
        View _view = _inflater.inflate(R.layout.m_ticket_pss_item, parent, false);

        return new ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder._passengerPhoto.setImageResource(_mTicketsListSelector.get(position).getBusPhoto());
        holder._passengerName.setText(_mTicketsListSelector.get(position).getPassengerName());
        holder._passengerSeat.setText(_mTicketsListSelector.get(position).getSeatNumber());
        holder._confirmationCode.setText(_mTicketsListSelector.get(position).getConfirmationNumber());
    }

    @Override
    public int getItemCount()
    {
        return _mTicketsListSelector.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView _passengerPhoto;
        public TextView _passengerName;
        public TextView _confirmationCode;
        public TextView _passengerSeat;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            _passengerPhoto = itemView.findViewById(R.id.m_ticket_passenger_photo);
            _passengerName = itemView.findViewById(R.id.m_ticket_passenger_name);
            _confirmationCode = itemView.findViewById(R.id.m_ticket_confirm_number);
            _passengerSeat = itemView.findViewById(R.id.m_ticket_passenger_seat_number);
        }
    }
}
