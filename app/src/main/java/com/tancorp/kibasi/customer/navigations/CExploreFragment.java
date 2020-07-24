package com.tancorp.kibasi.customer.navigations;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.isapanah.awesomespinner.AwesomeSpinner;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.customer.CBusSelectorFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class CExploreFragment extends Fragment
{
    public static final int EXPLORE_FRAGMENT_ID = 0;

    private List<String> _regions;
    private AwesomeSpinner _fromSpinner;
    private AwesomeSpinner _toSpinner;
    private Button _searchButton;
    private EditText _datePickerText;
    private DatePickerDialog _datePicker;
    private Animation _animBlinking;
    private ArrayList<String> _tempoStorage;

    private DatePickerDialog.OnDateSetListener _onDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        @SuppressLint("SetTextI18n")
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            _datePickerText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        }
    };


    public CExploreFragment()
    {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_explore, container, false);

        _fromSpinner = _view.findViewById(R.id.from_spinner);
        _toSpinner = _view.findViewById(R.id.to_spinner);
        _searchButton = _view.findViewById(R.id.search_button);
        _datePickerText = _view.findViewById(R.id.date_picker_text);

        _tempoStorage = new ArrayList<>();

        datePopup();
        prepareData();
        fromSpinner();
        toSpinner();

        _searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(_fromSpinner.getSelectedItem() == null || _toSpinner.getSelectedItem() == null || _fromSpinner.getSelectedItem().equals(_toSpinner.getSelectedItem()) || _datePickerText.length() == 0)
                {
                    spinnerErrorChecker();
                }
                else
                {
                    String _travelling_from = _fromSpinner.getSelectedItem();
                    String _travalling_to = _toSpinner.getSelectedItem();
                    String _date = _datePickerText.getText().toString();

                    _tempoStorage.add(_travelling_from);
                    _tempoStorage.add(_travalling_to);
                    _tempoStorage.add(_date);

                    Bundle _searchingData = new Bundle();
                    _searchingData.putStringArrayList("searching_results", _tempoStorage);

                    Fragment _busSelectorFragment = new CBusSelectorFragment();
                    loadFragment(_busSelectorFragment, _searchingData);
                }

            }
        });

        return _view;
    }

    private void errorAnimActivator()
    {
        _animBlinking = AnimationUtils.loadAnimation(getContext(), R.anim.blinking);

        _fromSpinner.startAnimation(_animBlinking);
        _toSpinner.startAnimation(_animBlinking);
    }


    private void spinnerErrorChecker()
    {
        if(_fromSpinner.getSelectedItem() == null || _toSpinner.getSelectedItem() == null || _fromSpinner.getSelectedItem().equals(_toSpinner.getSelectedItem()))
        {
            _fromSpinner.setBackgroundColor(getResources().getColor(R.color.colorErrorSpinner));
            _toSpinner.setBackgroundColor(getResources().getColor(R.color.colorErrorSpinner));
            errorAnimActivator();
        }
        else if(_fromSpinner.getSelectedItem() == null)
        {
            _fromSpinner.setBackgroundColor(getResources().getColor(R.color.colorErrorSpinner));

            _fromSpinner.startAnimation(_animBlinking);
        }
        else if(_toSpinner.getSelectedItem() == null)
        {
            _toSpinner.setBackgroundColor(getResources().getColor(R.color.colorErrorSpinner));

            _toSpinner.startAnimation(_animBlinking);
        }
        else if(_datePickerText.length() == 0)
        {
            _datePickerText.setError("Ingiza tarehe");
        }
    }

    private void datePopup()
    {
        _datePickerText.setInputType(InputType.TYPE_NULL);
        _datePickerText.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                final Calendar _calendar = Calendar.getInstance();
                int _day = _calendar.get(Calendar.DAY_OF_MONTH);
                int _month = _calendar.get(Calendar.MONTH);
                int _year = _calendar.get(Calendar.YEAR);

                datePickerDialog(_year, _month, _day);
            }
        });

        _datePickerText.setError(null);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void datePickerDialog(int _year, int _month, int _day)
    {
        _datePicker = new DatePickerDialog(Objects.requireNonNull(getContext()), _onDateSetListener, _year, _month, _day);
        _datePicker.show();
    }

    private void toSpinner()
    {
        spinnerColorChanger(_toSpinner);
        _toSpinner.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>()
        {
            @Override
            public void onItemSelected(int position, String itemAtPosition)
            {
                //Get the selected Item.
            }
        });

    }

    private void fromSpinner()
    {
        spinnerColorChanger(_fromSpinner);
        _fromSpinner.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>()
        {
            @Override
            public void onItemSelected(int position, String itemAtPosition)
            {
                //Get the selected Item.
            }
        });
    }

    private void spinnerColorChanger(AwesomeSpinner spinner)
    {
        spinner.setDownArrowTintColor(getResources().getColor(R.color.colorAccent));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void prepareData()
    {
        regionDataList();
        ArrayAdapter<String> _dataAdapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, _regions);

        _fromSpinner.setAdapter(_dataAdapter);
        _toSpinner.setAdapter(_dataAdapter);
    }

    private void regionDataList()
    {
        _regions = new ArrayList<>();
        _regions.add("Arusha");
        _regions.add("Dar es Salaam");
        _regions.add("Dodoma");
        _regions.add("Geita");
        _regions.add("Iringa");
        _regions.add("Kagera");
        _regions.add("Katavi");
        _regions.add("Kigoma");
        _regions.add("Kilimanjaro");
        _regions.add("Lindi");
        _regions.add("Manyara");
        _regions.add("Mara");
        _regions.add("Mbeya");
        _regions.add("Morogoro");
        _regions.add("Mtwara");
        _regions.add("Mwanza");
        _regions.add("Njombe");
        _regions.add("Pwani");
        _regions.add("Rukwa");
        _regions.add("Ruvuma");
        _regions.add("Shinyanga");
        _regions.add("Simiyu");
        _regions.add("Singida");
        _regions.add("Songwe");
        _regions.add("Tabora");
        _regions.add("Tanga");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void loadFragment(Fragment fragment, Bundle bundle)
    {
        fragment.setArguments(bundle);
        FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
