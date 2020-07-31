package com.tancorp.kibasi.mid;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.tancorp.kibasi.R;

public class MidMainActivity extends AppCompatActivity
{
    private FrameLayout _fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_main);
        _fragmentContainer = findViewById(R.id.mid_main_fragment_container);

        MidTicketFragment _fragment = new MidTicketFragment();
        openFragment(_fragment);

    }

    private void openFragment(Fragment fragment)
    {
        FragmentTransaction _transaction = getSupportFragmentManager().beginTransaction();
        _transaction.replace(_fragmentContainer.getId(), fragment);
        _transaction.commit();
    }


}