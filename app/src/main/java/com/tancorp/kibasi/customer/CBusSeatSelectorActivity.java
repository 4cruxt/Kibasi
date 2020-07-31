package com.tancorp.kibasi.customer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.tancorp.kibasi.R;
import com.tancorp.kibasi.customer.adapters.SeatAdapter;
import com.tancorp.kibasi.customer.models.Seat;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("ConstantConditions")
public class CBusSeatSelectorActivity extends AppCompatActivity
{

    public static final String LEFT_GRID_ID = "L";
    public static final String RIGHT_GRID_ID = "R";

    private GridView _bssLeftGridview;
    private GridView _bssRightGridview;
    private Button _bssPayButton;
    private TextView _bssSeatId;

    private ArrayList<Seat> _seatItemLeft;
    private ArrayList<Seat> _seatItemRight;

    private ArrayList<String> _selectedSeats; //todo: Make use of this selected variable since it holds position of selected seats.

    private SeatAdapter _seatAdapterL;
    private SeatAdapter _seatAdapterR;
    private TextView _bssFromText;
    private TextView _bssToText;
    private TextView _bssBusName;
    private TextView _bssBusJourneyTime;
    private TextView _bssBusDeparture;
    private TextView _bssBusTicketPrice;
    private TextView _bssBusArrivalTime;
    private int _busSeatNumber;
    private String _busPlateNumber;
    private String _dateTravel;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bus_seat_selector);

        _bssLeftGridview = findViewById(R.id.bss_left_gridview);
        _bssRightGridview = findViewById(R.id.bss_right_gridview);
        _bssPayButton = findViewById(R.id.bss_pay_button);
        _bssSeatId = findViewById(R.id.seat_id_text);
        _bssFromText = findViewById(R.id.bss_from_region_text);
        _bssToText = findViewById(R.id.bss_to_region_text);
        _bssBusName = findViewById(R.id.bss_bus_name);
        _bssBusJourneyTime = findViewById(R.id.bss_bus_journey_time);
        _bssBusDeparture = findViewById(R.id.bss_bus_depart_time);
        _bssBusTicketPrice = findViewById(R.id.bss_bus_ticket_price);
        _bssBusArrivalTime = findViewById(R.id.bss_bus_arrival_time);

        queryData();

        enablingGridviewData(_bssLeftGridview, _bssRightGridview);

        clickedGridviewItem(_seatAdapterL, _bssLeftGridview, LEFT_GRID_ID);
        clickedGridviewItem(_seatAdapterR, _bssRightGridview, RIGHT_GRID_ID);

    }


    private void enablingGridviewData(GridView viewL, GridView viewR)
    {
        _selectedSeats = new ArrayList<>();

        initGridview(viewL, viewR);
    }

    private void initGridview(GridView gridViewL, GridView gridViewR)
    {
        dataList();

        _seatAdapterL = new SeatAdapter(_seatItemLeft, LEFT_GRID_ID);
        gridViewL.setAdapter(_seatAdapterL);
        _seatAdapterR = new SeatAdapter(_seatItemRight, RIGHT_GRID_ID);
        gridViewR.setAdapter(_seatAdapterR);

    }

    private void queryData()
    {
        ArrayList<String> _searchingResults = getIntent().getStringArrayListExtra("searching_data");
        ArrayList<String> _cardDetailsHolder = getIntent().getStringArrayListExtra("bus_card");
        _busSeatNumber = getIntent().getIntExtra("bus_seat_number", 0);


        _bssBusName.setText(_cardDetailsHolder.get(0));
        _bssBusJourneyTime.setText(_cardDetailsHolder.get(1));
        _bssBusDeparture.setText(_cardDetailsHolder.get(2));
        _bssBusArrivalTime.setText(_cardDetailsHolder.get(3));
        _bssBusTicketPrice.setText(_cardDetailsHolder.get(4));

        _busPlateNumber = _cardDetailsHolder.get(5);
        _dateTravel = _searchingResults.get(2);

        _bssFromText.setText(_searchingResults.get(0));
        _bssToText.setText(_searchingResults.get(1));


    }

    private void dataList()
    {
        _seatItemLeft = new ArrayList<>();
        _seatItemRight = new ArrayList<>();

        Log.i("BUS SEAT NUMBER", String.valueOf(_busSeatNumber));

        for(int i = 0; i < _busSeatNumber / 2; i++)
        {
            _seatItemRight.add(new Seat(R.drawable.seat_selector, ""));
            _seatItemLeft.add(new Seat(R.drawable.seat_selector, ""));
        }


    }

    private void clickedGridviewItem(final SeatAdapter _seatAdapter, final GridView _gridView, final String gridId)
    {

        _gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int _itemPosition = position + 1;

                if(_seatAdapter._selectedSeatsPosition.contains(gridId + position))
                {
                    _seatAdapter._selectedSeatsPosition.remove(gridId + position);
                    ((CGridItemView) view).display(false);

                    _selectedSeats.remove(gridId + _itemPosition);

                    _seatAdapter._seats.get(position).setSeatIdText("");


                }
                else
                {
                    _seatAdapter._selectedSeatsPosition.add(gridId + position);
                    ((CGridItemView) view).display(true);

                    _selectedSeats.add(gridId + _itemPosition);

                    _seatAdapter._seats.get(position).setSeatIdText(gridId + _itemPosition);

                }

                _seatAdapter.notifyDataSetChanged();
                enablePayButton();


            }
        });
    }

    private void enablePayButton()
    {
        int _ticket = _selectedSeats.size();

        String _ticketText = "LIPA ";

        if(_ticket != 0)
        {
            String _ticketNumber = _ticketText + _ticket;

            _bssPayButton.setVisibility(View.VISIBLE);
            _bssPayButton.setText(_ticketNumber);

            //price format
            int _price = Integer.parseInt(_bssBusTicketPrice.getText().toString().replace("Tshs.", "").replace(",", "").replace("/=", "").trim());

            int _total = _price * _selectedSeats.size();
            String _totalPrice = "Tshs." + _total;

            final ArrayList<String> _paymentInfo = new ArrayList<>();

            _paymentInfo.add(_bssBusName.getText().toString());
            _paymentInfo.add(_busPlateNumber);
            _paymentInfo.add(String.valueOf(_price));
            _paymentInfo.add(_totalPrice);
            _paymentInfo.add(_dateTravel);
            //price format

            _bssPayButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent _ticketPayment = new Intent(CBusSeatSelectorActivity.this, CTicketPaymentActivity.class);
                    _ticketPayment.putStringArrayListExtra("selected_seats", _selectedSeats);
                    _ticketPayment.putStringArrayListExtra("payment_info", _paymentInfo);

                    startActivity(_ticketPayment);
                    finish();
                }
            });

        }
        else
        {
            _bssPayButton.setVisibility(View.INVISIBLE);
        }

    }

}
