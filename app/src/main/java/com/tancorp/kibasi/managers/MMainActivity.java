package com.tancorp.kibasi.managers;

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
import com.tancorp.kibasi.managers.navigations.MExploreFragment;
import com.tancorp.kibasi.managers.navigations.MManagerFragment;
import com.tancorp.kibasi.managers.navigations.MTicketFragment;

public class MMainActivity extends AppCompatActivity
{

    @SuppressLint("StaticFieldLeak")
    public static Activity mmainIntent;
    public static BottomNavigationView _navigationView;
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
                    _fragment = new MExploreFragment();
                    loadFragment(_fragment);
                    return true;
                case R.id.navigation_ticket:
                    _fragment = new MTicketFragment();
                    loadFragment(_fragment);
                    return true;
                case R.id.m_navigation_manager:
                    _fragment = new MManagerFragment();
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
        setContentView(R.layout.activity_m_main);
        mmainIntent = this;

        _toolbar = getSupportActionBar();

        _navigationView = findViewById(R.id.m_bottom_navigation_view);
        _navigationView.setOnNavigationItemSelectedListener(_onNavigationItemSelectedListener);

        int fragment_id = getIntent().getIntExtra("Fragment_id", 0);

        if(fragment_id == 2)
        {
            MManagerFragment _fragment = new MManagerFragment();
            loadFragment(_fragment);
            _navigationView.getMenu().getItem(2).setChecked(true);
        }
        else
        {
            MExploreFragment _fragment = new MExploreFragment();
            loadFragment(_fragment);
        }

    }

    //todo: make sure each opened fragment is active on its own navigation bottom view.
    private void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.m_fragment_container, fragment);
        transaction.commit();
    }

}