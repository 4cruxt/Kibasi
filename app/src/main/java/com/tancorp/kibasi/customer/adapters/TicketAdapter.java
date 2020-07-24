package com.tancorp.kibasi.customer.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.R;
import com.tancorp.kibasi.customer.CBookedTicketActivity;
import com.tancorp.kibasi.customer.CTicketPaymentActivity;
import com.tancorp.kibasi.customer.models.Ticket;


public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder>
{
    private Ticket[] _ticketListData;
    private boolean _isTicketPaid;


    public TicketAdapter(Ticket[] ticketListData, boolean isTicketPaid)
    {
        _ticketListData = ticketListData;
        _isTicketPaid = isTicketPaid;
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
        holder._busTicketImage.setImageResource(_ticketListData[position].getBusImageTicket());
        holder._dotVerifierTicketImage.setImageResource(_ticketListData[position].getDotVerifierTicket());
        holder._busTicketName.setText(_ticketListData[position].getBusNameTicket());
        holder._busTicketNumber.setText(_ticketListData[position].getBusNumberTicket());
        holder._busTicketSeat.setText(_ticketListData[position].getBusSeatTicket());
        holder._busTicketDate.setText(_ticketListData[position].getBusDateTicket());
        holder._layoutContainer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //todo: Implement clicks functionality on a paid ticket.
                if(_isTicketPaid)
                {
                    Intent _ticketIntent = new Intent(v.getContext(), CBookedTicketActivity.class);
                    v.getContext().startActivity(_ticketIntent);
                }
                else
                {
                    //todo: Implement clicks functionality on an upaid ticket.
                    Intent _paymentIntent = new Intent(v.getContext(), CTicketPaymentActivity.class);
                    v.getContext().startActivity(_paymentIntent);
                }

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
        private ImageView _busTicketImage;
        private ImageView _dotVerifierTicketImage;
        private TextView _busTicketName;
        private TextView _busTicketNumber;
        private TextView _busTicketSeat;
        private TextView _busTicketDate;
        private ConstraintLayout _layoutContainer;

        private ViewHolder(@NonNull final View itemView)
        {
            super(itemView);
            this._busTicketImage = itemView.findViewById(R.id.booked_bus_ticket_image);
            this._dotVerifierTicketImage = itemView.findViewById(R.id.dot_icon_verifier);
            this._busTicketName = itemView.findViewById(R.id.booked_bus_ticket_name);
            this._busTicketNumber = itemView.findViewById(R.id.booked_bus_number);
            this._busTicketSeat = itemView.findViewById(R.id.booked_bus_seat);
            this._busTicketDate = itemView.findViewById(R.id.booked_ticket_date);
            this._layoutContainer = itemView.findViewById(R.id.ticket_container);

        }


    }
}
