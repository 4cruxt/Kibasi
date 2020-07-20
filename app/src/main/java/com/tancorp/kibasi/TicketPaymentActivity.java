package com.tancorp.kibasi;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import static com.tancorp.kibasi.navigations.TicketFragment.TICKET_FRAGMENT_ID;

public class TicketPaymentActivity extends AppCompatActivity
{
    private Button _payerActivatorButton;
    private EditText _travelerName;
    private EditText _payerName;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_payment);

        _payerActivatorButton = findViewById(R.id.payer_payment_activator_button);
        _travelerName = findViewById(R.id.traveler_name);
        _payerName = findViewById(R.id.payer_name);

        paymentTimeActivator();
    }


    private void paymentTimeActivator()
    {
        /*todo: Create a timer of 15 minutes after the button has been clicked to ensure that payments
                are done <= 15 minutes starting from the button click.
                Send the traveler's name and payer name to the database.
         */

        _payerActivatorButton.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v)
            {
                if(_travelerName.length() == 0 || _payerName.length() == 0)
                {
                    errorChecker();
                }
                else
                {
                    showDialogPopUp();
                }
            }
        });

    }

    private void errorChecker()
    {
        if(_travelerName.length() == 0 && _payerName.length() == 0)
        {
            _travelerName.setError("Msafiri?");
            _payerName.setError("Mlipaji?");
        }
        else if(_travelerName.length() == 0)
        {
            _travelerName.setError("Msafiri?");
        }
        else if(_payerName.length() == 0)
        {
            _payerName.setError("Mlipaji?");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showDialogPopUp()
    {
        final Dialog _paymentDialog = new Dialog(TicketPaymentActivity.this);
        _paymentDialog.setContentView(R.layout.payment_dialog);
        _paymentDialog.setCancelable(true);
        Objects.requireNonNull(_paymentDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button _cancelButton = _paymentDialog.findViewById(R.id.cancel_button);
        Button _acceptButton = _paymentDialog.findViewById(R.id.accept_button);

        _cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _paymentDialog.dismiss();
            }
        });

        _acceptButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //todo: Get journey places(from, to), date, bus seat number, time to leave, bus plate - number, traveler and payer.

                String _travelerNames = _travelerName.getText().toString();
                String _payerNames = _payerName.getText().toString();


                Toast.makeText(TicketPaymentActivity.this, "Msafiri: " + _travelerNames + "\n" + "Mlipaji: " + _payerNames, Toast.LENGTH_SHORT).show();
                _paymentDialog.dismiss();

                Intent _mainIntent = new Intent(TicketPaymentActivity.this, MainActivity.class);
                _mainIntent.putExtra("Fragment_id", "" + TICKET_FRAGMENT_ID);
                MainActivity.self_intent.finish();
                startActivity(_mainIntent);
                finish();

            }
        });
        _paymentDialog.show();
    }


}
