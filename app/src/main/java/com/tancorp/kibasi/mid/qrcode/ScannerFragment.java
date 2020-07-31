package com.tancorp.kibasi.mid.qrcode;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tancorp.kibasi.R;

public class ScannerFragment extends Fragment
{
    private CodeScanner _codeScanner;
    private CodeScannerView _codeScannerView;
    private Activity _activity;
    private TextView _passengerName;
    private Button _verifierButton;

    public ScannerFragment()
    {
        //Required
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        _activity = getActivity();
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_scanner, container, false);

        _codeScannerView = _view.findViewById(R.id.mid_code_scanner);
        _passengerName = _view.findViewById(R.id.mid_scanned_pss_name);
        _verifierButton = _view.findViewById(R.id.mid_scanned_verifier_button);

        setupScanner();

        return _view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        requestFromCamera();

    }

    private void setupScanner()
    {
        _codeScanner = new CodeScanner(_activity, _codeScannerView);
        _codeScanner.setDecodeCallback(new DecodeCallback()
        {
            @Override
            public void onDecoded(@NonNull final Result result)
            {
                _activity.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        String[] _results = result.getText().split(",");
                        boolean _hasPaid = Boolean.parseBoolean(_results[0]);
                        String _date = _results[1];
                        String _seat = _results[2];
                        String _bus = _results[3];
                        String _confirmationCode = _results[4];
                        String _passengerName = _results[5].replace("]", "");

                        scannedResultHolder(_hasPaid, _passengerName, _confirmationCode, _date, _seat, _bus);
                    }
                });
            }
        });

        _verifierButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _codeScanner.startPreview();

                _passengerName.setVisibility(View.INVISIBLE);
                _verifierButton.setVisibility(View.INVISIBLE);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void scannedResultHolder(boolean hasPaid, String passengerName, String confirmationCode, String date, String seat, String bus)
    {
        _passengerName.setVisibility(View.VISIBLE);
        _verifierButton.setVisibility(View.VISIBLE);

        _passengerName.setText(passengerName);

        if(hasPaid)
        {
            _verifierButton.setText("Amelipa");
        }
        else
        {
            _verifierButton.setText("Hajalipa");
        }

        //todo: Send extra information to the database so as to be displayed in the user's recyclerview.

        /*
         * Information to be send to the database are hasPaid, passenger name, confirmation code, date, seat, bus
         */

    }

    private void requestFromCamera()
    {
        Dexter.withContext(_activity).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener()
        {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse)
            {
                _codeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse)
            {
                Toast.makeText(_activity, "Ruhusu camera itumike", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken)
            {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    @Override
    public void onPause()
    {
        _codeScanner.releaseResources();
        super.onPause();
    }


}