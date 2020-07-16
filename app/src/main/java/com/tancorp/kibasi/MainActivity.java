package com.tancorp.kibasi;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tancorp.kibasi.navigations.ExploreFragment;
import com.tancorp.kibasi.navigations.PassengerFragment;
import com.tancorp.kibasi.navigations.TicketFragment;

public class MainActivity extends AppCompatActivity
{

    private ActionBar _toolbar;
    private Fragment _fragment;
    private BottomNavigationView.OnNavigationItemSelectedListener _onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch(item.getItemId())
            {
                case R.id.navigation_explore:
                    _fragment = new ExploreFragment();
                    loadFragment(_fragment);
                    return true;
                case R.id.navigation_ticket:
                    _fragment = new TicketFragment();
                    loadFragment(_fragment);
                    return true;
                case R.id.navigation_passenger:
                    _fragment = new PassengerFragment();
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

        _toolbar = getSupportActionBar();

        BottomNavigationView _navigationView = findViewById(R.id.bottom_navigation_view);
        _navigationView.setOnNavigationItemSelectedListener(_onNavigationItemSelectedListener);

        _fragment = new ExploreFragment();
        loadFragment(_fragment);
    }

    private void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
