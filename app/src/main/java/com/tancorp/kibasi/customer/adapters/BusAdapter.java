package com.tancorp.kibasi.customer.adapters;

import android.content.Context;
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
import com.tancorp.kibasi.customer.CBusSeatSelectorActivity;
import com.tancorp.kibasi.customer.models.Bus;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ViewHolder>
{
    private Bus[] _busListData;
    private Context _context;
    private ArrayList<String> _queryData;

    public BusAdapter(Context context, Bus[] busListData, ArrayList<String> list)
    {
        this._context = context;
        this._busListData = busListData;
        this._queryData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater _layoutInflater = LayoutInflater.from(parent.getContext());
        View _busListItem = _layoutInflater.inflate(R.layout.bus_selector_item, parent, false);

        return new ViewHolder(_busListItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        //todo: remember to add bus image here.
//        holder._busImage.setImageResource();
        holder._busName.setText(_busListData[position].getBusName());
        holder._busJourneyTimeText.setText(_busListData[position].getBusJourneyTime());
        holder._busTicketPrice.setText(_busListData[position].getBusTicket());
        holder._busArrivalTime.setText(_busListData[position].getBusArrivalTime());
        holder._busDepartTime.setText(_busListData[position].getBusDepartTime());

        //bus passed data
        final ArrayList<String> _busDataHolder = new ArrayList<>();
        _busDataHolder.add(_busListData[position].getBusName());
        _busDataHolder.add(_busListData[position].getBusJourneyTime());
        _busDataHolder.add(_busListData[position].getBusDepartTime());
        _busDataHolder.add(_busListData[position].getBusArrivalTime());
        _busDataHolder.add(_busListData[position].getBusTicket());

        holder._layoutContainer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent _seatSelectorIntent = new Intent(v.getContext(), CBusSeatSelectorActivity.class);
                _seatSelectorIntent.putStringArrayListExtra("searching_data", _queryData);
                _seatSelectorIntent.putStringArrayListExtra("bus_card", _busDataHolder);
                v.getContext().startActivity(_seatSelectorIntent);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return _busListData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView _busImage;
        public TextView _busJourneyTimeText;
        public TextView _busName;
        public TextView _busTicketPrice;
        public TextView _busDepartTime;
        public TextView _busArrivalTime;
        public ConstraintLayout _layoutContainer;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
//            this._busImage = itemView.findViewById(R.id.bus_image);
            this._busName = itemView.findViewById(R.id.bss_bus_name);
            this._busJourneyTimeText = itemView.findViewById(R.id.bss_bus_journey_time);
            this._busTicketPrice = itemView.findViewById(R.id.bss_bus_ticket_price);
            this._layoutContainer = itemView.findViewById(R.id.bus_item_card_container);
            this._busDepartTime = itemView.findViewById(R.id.bss_bus_depart_time);
            this._busArrivalTime = itemView.findViewById(R.id.bss_bus_arrival_time);
        }
    }
}
