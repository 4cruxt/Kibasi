package com.tancorp.kibasi.managers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.models.MExploreVerifier;

import java.util.ArrayList;

public class MExploreVerifierAdapter extends RecyclerView.Adapter<MExploreVerifierAdapter.ViewHolder>
{
    private ArrayList<MExploreVerifier> _ticketVerifiers;

    public MExploreVerifierAdapter(ArrayList<MExploreVerifier> ticketVerifiers)
    {
        _ticketVerifiers = ticketVerifiers;
    }

    @NonNull
    @Override
    public MExploreVerifierAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater _inflater = LayoutInflater.from(parent.getContext());
        View _view = _inflater.inflate(R.layout.m_e_ticket_item, parent, false);

        return new ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MExploreVerifierAdapter.ViewHolder holder, int position)
    {
        String passengerName = _ticketVerifiers.get(position).getPassengerName();
        String payerName = _ticketVerifiers.get(position).getPayerName();
        String confirmation = _ticketVerifiers.get(position).getConfirmationCode();

        holder.passengerName(passengerName);
        holder.payerName(payerName);
        holder.confirmationCode(confirmation);

        holder._paidButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Ticket has been paid checked
                Toast.makeText(v.getContext(), "Tiketi imethibitishwa", Toast.LENGTH_SHORT).show();

            }
        });

        holder._unpaidButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //ticket has not been paid checked
                Toast.makeText(v.getContext(), "Tiketi haijalipiwa", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return _ticketVerifiers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView _passengerName;
        private TextView _payerName;
        private TextView _confirmationCode;
        private Button _paidButton;
        private Button _unpaidButton;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            _passengerName = itemView.findViewById(R.id.m_e_pss_name);
            _payerName = itemView.findViewById(R.id.m_e_payer_name);
            _confirmationCode = itemView.findViewById(R.id.m_e_confirmation_number);
            _paidButton = itemView.findViewById(R.id.m_e_paid_button);
            _unpaidButton = itemView.findViewById(R.id.m_e_unpaid_button);

        }

        private void passengerName(String passengerName)
        {
            _passengerName.setText(passengerName);
        }

        private void payerName(String payerName)
        {
            _payerName.setText(payerName);
        }

        private void confirmationCode(String confirmationCode)
        {
            _confirmationCode.setText(confirmationCode);
        }
    }
}
