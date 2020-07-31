package com.tancorp.kibasi.mid.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tancorp.kibasi.BoardActivity;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.mid.MidMainActivity;

public class MidLoginActivity extends AppCompatActivity
{
    private Button _submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_login);
        _submitButton = findViewById(R.id.mid_otp_submit_button);

        _submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                BoardActivity.selfIntent.finish();
                Intent mainIntent = new Intent(MidLoginActivity.this, MidMainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }
}