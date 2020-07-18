package com.tancorp.kibasi;

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

import com.tancorp.kibasi.adapters.SeatAdapter;
import com.tancorp.kibasi.models.Seat;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("ConstantConditions")
public class BusSeatSelectorActivity extends AppCompatActivity
{

    public static final String LEFT_GRID_ID = "L";
    public static final String RIGHT_GRID_ID = "R";

    private GridView _bssLeftGridview;
    private GridView _bssRightGridview;
    private Button _bssPayButton;
    private TextView _bssSeatId;

    private Seat[] _seatItemLeft;
    private Seat[] _seatItemRight;

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


        _bssBusName.setText(_cardDetailsHolder.get(0));
        _bssBusJourneyTime.setText(_cardDetailsHolder.get(1));
        _bssBusDeparture.setText(_cardDetailsHolder.get(2));
        _bssBusArrivalTime.setText(_cardDetailsHolder.get(3));
        _bssBusTicketPrice.setText(_cardDetailsHolder.get(4));

        _bssFromText.setText(_searchingResults.get(0));
        _bssToText.setText(_searchingResults.get(1));

    }

    private void dataList()
    {
        _seatItemLeft = new Seat[]{new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""),

        };

        _seatItemRight = new Seat[]{new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""), new Seat(R.drawable.seat_selector, ""),};

    }

    private void clickedGridviewItem(final SeatAdapter _seatAdapter, final GridView _gridView, final String gridId)
    {

        _gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int _itemPosition = position + 1;
                //todo: Do something when a seat icon is pressed.

                if(_seatAdapter._selectedSeatsPosition.contains(gridId + position))
                {
                    _seatAdapter._selectedSeatsPosition.remove(gridId + position);
                    ((GridItemView) view).display(false);

                    _selectedSeats.remove(gridId + _itemPosition);

                    _seatAdapter._seats[position].setSeatIdText("");


                }
                else
                {
                    _seatAdapter._selectedSeatsPosition.add(gridId + position);
                    ((GridItemView) view).display(true);

                    _selectedSeats.add(gridId + _itemPosition);

                    _seatAdapter._seats[position].setSeatIdText(gridId + _itemPosition);

                }

                _seatAdapter.notifyDataSetChanged();
                enablePayButton();

                Log.i("SELECTED SEATS", _selectedSeats.toString());
                Log.i("SEATS IN ADAPTER", _seatAdapter._selectedSeatsPosition.toString());

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

            _bssPayButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent _ticketPayment = new Intent(BusSeatSelectorActivity.this, TicketPaymentActivity.class);
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
