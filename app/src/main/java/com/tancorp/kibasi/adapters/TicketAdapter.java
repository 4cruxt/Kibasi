package com.tancorp.kibasi.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.BookedTicketActivity;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.models.Ticket;


public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder>
{
    private Ticket[] _ticketListData;

    public TicketAdapter(Ticket[] ticketListData)
    {
        _ticketListData = ticketListData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater _layoutInflater = LayoutInflater.from(parent.getContext());
        View _ticketListItem = _layoutInflater.inflate(R.layout.ticket_item, parent, false);
        return new ViewHolder(_ticketListItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position)
    {
//        holder._busTicketImage.setImageResource();
        holder._busTicketName.setText(_ticketListData[position].getBusNameTicket());
        holder._busTicketDate.setText(_ticketListData[position].getBusDateTicket());
        holder._layoutContainer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //todo: Implement clicks functionality on a ticket.
                Toast.makeText(v.getContext(), _ticketListData[position].getBusNameTicket() + " selected!", Toast.LENGTH_SHORT).show();
                Intent _ticketIntent = new Intent(v.getContext(), BookedTicketActivity.class);
                v.getContext().startActivity(_ticketIntent);
            }
        });

    }


    @Override
    public int getItemCount()
    {
        return _ticketListData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView _busTicketImage;
        public TextView _busTicketName;
        public TextView _busTicketDate;
        public ConstraintLayout _layoutContainer;

        public ViewHolder(@NonNull final View itemView)
        {
            super(itemView);
            this._busTicketImage = itemView.findViewById(R.id.booked_bus_ticket_image);
            this._busTicketName = itemView.findViewById(R.id.booked_bus_ticket_name);
            this._busTicketDate = itemView.findViewById(R.id.booked_ticket_date);
            this._layoutContainer = itemView.findViewById(R.id.ticket_container);

        }


    }
}
