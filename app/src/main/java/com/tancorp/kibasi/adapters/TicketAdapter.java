package com.tancorp.kibasi.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.BookedTicketFragment;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.models.Ticket;

import java.util.Objects;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
//        holder._busTicketImage.setImageResource();
        holder._busTicketName.setText(_ticketListData[position].getBusNameTicket());
        holder._busTicketDate.setText(_ticketListData[position].getBusDateTicket());

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

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this._busTicketImage = itemView.findViewById(R.id.booked_bus_ticket_image);
            this._busTicketName = itemView.findViewById(R.id.booked_bus_ticket_name);
            this._busTicketDate = itemView.findViewById(R.id.booked_ticket_date);
            this._layoutContainer = itemView.findViewById(R.id.ticket_container);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v)
                {
                    Fragment _bookedTicketFragment = new BookedTicketFragment();
                    loadFragment(_bookedTicketFragment);
                }
            });

        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private void loadFragment(Fragment fragment)
        {
            //todo: Make a fragment start another fragment using the same activity the main activity.
            FragmentTransaction transaction = Objects.requireNonNull(fragment.getActivity()).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }
}