package com.tancorp.kibasi;

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


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bus_seat_selector);

        _bssLeftGridview = findViewById(R.id.bss_left_gridview);
        _bssRightGridview = findViewById(R.id.bss_right_gridview);
        _bssPayButton = findViewById(R.id.bss_pay_button);
        _bssSeatId = findViewById(R.id.seat_id_text);

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

        }
        else
        {
            _bssPayButton.setVisibility(View.INVISIBLE);
        }

    }


}
