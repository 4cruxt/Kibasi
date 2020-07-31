package com.tancorp.kibasi.managers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.models.MPMid;

import java.util.ArrayList;

public class MPMidAdapter extends RecyclerView.Adapter<MPMidAdapter.ViewHolder>
{
    ArrayList<MPMid> _mpMids;

    public MPMidAdapter(ArrayList<MPMid> mpMids)
    {
        _mpMids = mpMids;
    }

    @NonNull
    @Override
    public MPMidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater _inflater = LayoutInflater.from(parent.getContext());
        View _view = _inflater.inflate(R.layout.m_p_mid_item, parent, false);

        return new ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull MPMidAdapter.ViewHolder holder, int position)
    {
        holder.setMidPhoto(_mpMids.get(position).getMidPhoto());
        holder.setMidName(_mpMids.get(position).getMidName());
        holder.setMidPhone(_mpMids.get(position).getMidPhone());
        holder.setMidBus(_mpMids.get(position).getMidBus());

    }

    @Override
    public int getItemCount()
    {
        return _mpMids.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView _midPhoto;
        private TextView _midName;
        private TextView _midPhone;
        private TextView _midBus;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            _midPhoto = itemView.findViewById(R.id.m_p_mid_photo);
            _midName = itemView.findViewById(R.id.m_p_mid_name);
            _midPhone = itemView.findViewById(R.id.m_p_mid_phone);
            _midBus = itemView.findViewById(R.id.m_p_mid_bus_number);
        }

        public void setMidPhoto(int midPhoto)
        {
            _midPhoto.setImageResource(midPhoto);
        }

        public void setMidName(String midName)
        {
            _midName.setText(midName);
        }

        public void setMidPhone(String midPhone)
        {
            _midPhone.setText(midPhone);
        }

        public void setMidBus(String midBus)
        {
            _midBus.setText(midBus);
        }
    }
}
