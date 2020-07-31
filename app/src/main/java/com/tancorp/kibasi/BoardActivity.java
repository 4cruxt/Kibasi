package com.tancorp.kibasi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tancorp.kibasi.customer.authentications.CAuthActivity;
import com.tancorp.kibasi.managers.authentications.MAuthActivity;
import com.tancorp.kibasi.mid.authentication.MidLoginActivity;

public class BoardActivity extends AppCompatActivity
{
    @SuppressLint("StaticFieldLeak")
    public static Activity selfIntent;

    private Button _customerButton;
    private Button _midbutton;
    private Button _managerButton;
    private FirebaseAuth _firebaseAuth;
    private FirebaseUser _currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        selfIntent = this;

        _customerButton = findViewById(R.id.board_customer_button);
        _midbutton = findViewById(R.id.board_mid_button);
        _managerButton = findViewById(R.id.board_manager_button);

        _firebaseAuth = FirebaseAuth.getInstance();
        _currentuser = _firebaseAuth.getCurrentUser();

        enableService();
    }

//    @Override
//    protected void onStart()
//    {
//        super.onStart();
//        if(_currentuser != null)
//        {
//            Intent authCustomerIntent = new Intent(BoardActivity.this, CAuthActivity.class);
//            startActivity(authCustomerIntent);
//            finish();
//        }
//    }

    private void enableService()
    {
        enableCustomer();
        enableMid();
        enableManager();
    }

    private void enableCustomer()
    {
        _customerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent authCustomerIntent = new Intent(BoardActivity.this, CAuthActivity.class);
                startActivity(authCustomerIntent);
            }
        });
    }

    private void enableMid()
    {
        _midbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent authMidIntent = new Intent(BoardActivity.this, MidLoginActivity.class);
                startActivity(authMidIntent);

            }
        });
    }

    private void enableManager()
    {
        _managerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent authManagerIntent = new Intent(BoardActivity.this, MAuthActivity.class);
                startActivity(authManagerIntent);
            }
        });
    }


}