package com.tancorp.kibasi.managers.authentications;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.tancorp.kibasi.R;


public class MAuthActivity extends AppCompatActivity
{
    private FrameLayout _authSliderContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_auth);

        _authSliderContainer = findViewById(R.id.auth_slider_container);

        setFragment(new MSignInFragment());

    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction _transaction = getSupportFragmentManager().beginTransaction();
        _transaction.replace(_authSliderContainer.getId(), fragment);
        _transaction.commit();
    }
}