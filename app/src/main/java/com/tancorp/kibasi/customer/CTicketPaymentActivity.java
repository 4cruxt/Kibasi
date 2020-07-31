package com.tancorp.kibasi.customer;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;
import com.tancorp.kibasi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.tancorp.kibasi.customer.navigations.CTicketFragment.TICKET_FRAGMENT_ID;

public class CTicketPaymentActivity extends AppCompatActivity
{
    private Button _payerActivatorButton;
    private NachoTextView _travelerName;
    private EditText _payerName;
    private TextView _moneyToPay;
    private ArrayList<String> _selectedSeats;
    private ArrayList<String> _paymentInfo;
    private ArrayList<String> _travelerNames;
    private TextView _busNameText;
    private TextView _pricePerTicketText;
    private String _busName;
    private String _busPlateNumber;
    private String _pricePerTicket;
    private String _totalPayment;
    private String _dateTravel;
    private FirebaseFirestore _firestore;
    private FirebaseUser _CurrentUser;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_payment);

        _payerActivatorButton = findViewById(R.id.payer_payment_activator_button);
        _travelerName = findViewById(R.id.traveler_name);
        _payerName = findViewById(R.id.payer_name);
        _moneyToPay = findViewById(R.id.money_to_pay);
        _busNameText = findViewById(R.id.bpd_bus_name_text);
        _pricePerTicketText = findViewById(R.id.bpd_ticket_price_text);

        _firestore = FirebaseFirestore.getInstance();
        FirebaseAuth _auth = FirebaseAuth.getInstance();
        _CurrentUser = _auth.getCurrentUser();

        _selectedSeats = getIntent().getStringArrayListExtra("selected_seats");
        _paymentInfo = getIntent().getStringArrayListExtra("payment_info");

        queryData();

        _travelerName.addChipTerminator(',', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);

        paymentTimeActivator();
    }

    @SuppressLint("SetTextI18n")
    private void queryData()
    {
        //todo: pass some below data into firebase database to be queried later for more use.
        _busName = _paymentInfo.get(0);
        _busPlateNumber = _paymentInfo.get(1);
        _pricePerTicket = _paymentInfo.get(2);
        _totalPayment = _paymentInfo.get(3);
        _dateTravel = _paymentInfo.get(4);

        _moneyToPay.setText(_totalPayment);
        _busNameText.setText(_busName);
        _pricePerTicketText.setText(_pricePerTicket + "/=");

    }

    private void extractInputs()
    {
        if(_selectedSeats.size() == _travelerName.getAllChips().size())
        {
            _travelerNames = (ArrayList<String>) _travelerName.getChipValues();
            showDialogPopUp();
        }
        else if(_selectedSeats.size() > _travelerName.getAllChips().size())
        {
            Toast.makeText(this, "Tiketi zimezidi!", Toast.LENGTH_SHORT).show();
        }
        else if(_selectedSeats.size() < _travelerName.getAllChips().size())
        {
            Toast.makeText(this, "Umechagua tiketi chache!", Toast.LENGTH_SHORT).show();
        }

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
                    extractInputs();
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
        final Dialog _paymentDialog = new Dialog(CTicketPaymentActivity.this);
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

                String _payerNames = _payerName.getText().toString();

                Toast.makeText(CTicketPaymentActivity.this, "Msafiri: " + _travelerNames + "\n" + "Mlipaji: " + _payerNames, Toast.LENGTH_SHORT).show();
                _paymentDialog.dismiss();

                uploadDataToFirestore(_travelerNames, _payerNames);

            }
        });
        _paymentDialog.show();
    }

    private void uploadDataToFirestore(ArrayList<String> traveler, String payer)
    {
        Map<String, Object> _ticketData = new HashMap<>();
        _ticketData.put("traveler", traveler);
        _ticketData.put("payer", payer);
        _ticketData.put("tickets", _selectedSeats);
        _ticketData.put("price_per_ticket", _pricePerTicket);
        _ticketData.put("total_payment", _totalPayment);
        _ticketData.put("bus_name", _busName);
        _ticketData.put("bus_plate_number", _busPlateNumber);
        _ticketData.put("travel_date", _dateTravel);
        _ticketData.put("has_paid", false);
        _ticketData.put("phone_owner", _CurrentUser.getPhoneNumber());

        Map<String, Object> _busNumber = new HashMap<>();
        _busNumber.put("latest_bus_number", _busPlateNumber);

        for(String seat : _selectedSeats)
        {
            _firestore.collection("TICKETS").document(_dateTravel.replace('/', '-')).collection(_busName).document(seat).set(_ticketData, SetOptions.merge());
        }


        _firestore.collection("TICKETS").document(_dateTravel.replace('/', '-')).set(_busNumber, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    userTickets();
                    openMainIntent();
                }
                else
                {
                    Log.i("FIRESTORE ERROR", Objects.requireNonNull(Objects.requireNonNull(task.getException()).getMessage()));
                }
            }
        });

    }

    private void userTickets()
    {
        Map<String, Object> _userTicketsInfo = new HashMap<>();
        _userTicketsInfo.put("date_travel", _dateTravel.replace('/', '-'));
        _userTicketsInfo.put("bus_name", _busName);
        _userTicketsInfo.put("ticket_seats", _selectedSeats);

        _firestore.collection("TRAVELLERS").document(Objects.requireNonNull(_CurrentUser.getPhoneNumber())).set(_userTicketsInfo, SetOptions.merge());
    }

    private void openMainIntent()
    {
        CMainActivity.self_intent.finish();
        Intent _mainIntent = new Intent(CTicketPaymentActivity.this, CMainActivity.class);
        _mainIntent.putExtra("Fragment_id", "" + TICKET_FRAGMENT_ID);
        startActivity(_mainIntent);
        finish();
    }

}
