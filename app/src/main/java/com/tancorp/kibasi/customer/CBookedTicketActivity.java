package com.tancorp.kibasi.customer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.tancorp.kibasi.R;

import java.util.ArrayList;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CBookedTicketActivity extends AppCompatActivity
{

    private TextView _dateTicketBookedText;
    private TextView _seatTicketBookedText;
    private TextView _busNumberText;
    private TextView _confirmationCodeText;
    private TextView _passengerNameText;
    private ImageView _qrCodeImage;
    private boolean _hasPaid;
    private ArrayList<String> _qrData;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booked_ticket);

        _dateTicketBookedText = findViewById(R.id.date_ticket_booked_text);
        _seatTicketBookedText = findViewById(R.id.seat_ticket_booked_text);
        _busNumberText = findViewById(R.id.bus_number_text);
        _confirmationCodeText = findViewById(R.id.confirmation_code_text);
        _passengerNameText = findViewById(R.id.passenger_name_text);
        _qrCodeImage = findViewById(R.id.qrcode_image);

        generateQR();
    }

    private void generateQR()
    {
        _qrData = new ArrayList<>();

        String _isPaid = String.valueOf(_hasPaid);
        String _date = _dateTicketBookedText.getText().toString();
        String _seat = _seatTicketBookedText.getText().toString();
        String _busNumber = _busNumberText.getText().toString();
        String _confirmationCode = _confirmationCodeText.getText().toString();
        String _passengerName = _passengerNameText.getText().toString();

        _qrData.add(_isPaid);
        _qrData.add(_date);
        _qrData.add(_seat);
        _qrData.add(_busNumber);
        _qrData.add(_confirmationCode);
        _qrData.add(_passengerName);

        QRGEncoder _encoder = new QRGEncoder(_qrData.toString(), null, QRGContents.Type.TEXT, 500);
        _encoder.setColorBlack(getResources().getColor(R.color.colorBackgroundWhite));
        _encoder.setColorWhite(getResources().getColor(R.color.colorAccent));
        try
        {
            Bitmap _bitmap = _encoder.getBitmap();
            _qrCodeImage.setImageBitmap(_bitmap);
        }
        catch(Exception e)
        {
            Log.v("QRCODE ERROR", e.toString());
        }
    }


}
