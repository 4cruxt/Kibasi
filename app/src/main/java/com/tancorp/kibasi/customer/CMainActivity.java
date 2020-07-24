package com.tancorp.kibasi.customer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.customer.navigations.CExploreFragment;
import com.tancorp.kibasi.customer.navigations.CPassengerFragment;
import com.tancorp.kibasi.customer.navigations.CTicketFragment;

import static com.tancorp.kibasi.customer.navigations.CExploreFragment.EXPLORE_FRAGMENT_ID;
import static com.tancorp.kibasi.customer.navigations.CPassengerFragment.PASSENGER_FRAGMENT_ID;
import static com.tancorp.kibasi.customer.navigations.CTicketFragment.TICKET_FRAGMENT_ID;

public class CMainActivity extends AppCompatActivity
{

    @SuppressLint("StaticFieldLeak")
    public static Activity self_intent;

    private ActionBar _toolbar;
    private Fragment _fragment;
    public BottomNavigationView _navigationView;
    private String _fragment_id;

    private BottomNavigationView.OnNavigationItemSelectedListener _onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch(item.getItemId())
            {
                case R.id.navigation_explore:
                    _fragment = new CExploreFragment();
                    loadFragment(_fragment);
                    return true;
                case R.id.navigation_ticket:
                    _fragment = new CTicketFragment();
                    loadFragment(_fragment);
                    return true;
                case R.id.navigation_passenger:
                    _fragment = new CPassengerFragment();
                    loadFragment(_fragment);
                    return true;
            }

            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        self_intent = this;

        _toolbar = getSupportActionBar();

        _navigationView = findViewById(R.id.bottom_navigation_view);
        _navigationView.setOnNavigationItemSelectedListener(_onNavigationItemSelectedListener);

        _fragment_id = getIntent().getStringExtra("Fragment_id");

        fragmentSwitch();


    }

    private void fragmentSwitch()
    {
        if(_fragment_id != null)
        {
            if(_fragment_id.contains("" + EXPLORE_FRAGMENT_ID))
            {
                _fragment = new CExploreFragment();
                loadFragment(_fragment);
                _navigationView.getMenu().getItem(EXPLORE_FRAGMENT_ID).setChecked(true);
            }
            else if(_fragment_id.contains("" + TICKET_FRAGMENT_ID))
            {
                _fragment = new CTicketFragment();
                loadFragment(_fragment);
                _navigationView.getMenu().getItem(TICKET_FRAGMENT_ID).setChecked(true);
            }
            else if(_fragment_id.contains("" + PASSENGER_FRAGMENT_ID))
            {
                _fragment = new CPassengerFragment();
                loadFragment(_fragment);
                _navigationView.getMenu().getItem(PASSENGER_FRAGMENT_ID).setChecked(true);
            }

        }
        else
        {
            _fragment = new CExploreFragment();
            loadFragment(_fragment);
        }
    }

    //todo: make sure each opened fragment is active on its own navigation bottom view.
    private void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
