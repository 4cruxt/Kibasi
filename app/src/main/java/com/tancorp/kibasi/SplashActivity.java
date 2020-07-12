package com.tancorp.kibasi;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent mainIntent = new Intent(this, OnboardScreenActivity.class);
        startActivity(mainIntent);
        finish();

    }
}
