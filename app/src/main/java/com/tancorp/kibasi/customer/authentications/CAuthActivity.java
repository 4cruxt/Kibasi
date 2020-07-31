package com.tancorp.kibasi.customer.authentications;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.tancorp.kibasi.BoardActivity;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.customer.CMainActivity;
import com.tancorp.kibasi.customer.COnboardScreenActivity;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CAuthActivity extends AppCompatActivity
{
    public String _userName;
    private Button _otpButton;
    private EditText _otpPassengerName, _otpUserNumber, _otpNumber;
    private ProgressBar _progressBar;
    private FirebaseAuth _firebaseAuth;
    private FirebaseUser _currentuser;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks _callbacks;
    private String _phone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_auth);

        _otpPassengerName = findViewById(R.id.c_auth_opt_pss_name);
        _otpButton = findViewById(R.id._otp_verifier_button);
        _otpUserNumber = findViewById(R.id.c_auth_opt_phone_number);
        _otpNumber = findViewById(R.id.c_auth_opt_number);
        _progressBar = findViewById(R.id.c_auth_opt_progressBar);

        _firebaseAuth = FirebaseAuth.getInstance();
        _currentuser = _firebaseAuth.getCurrentUser();

        verificationPassed();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(_currentuser != null)
        {
            sendUserToHome();
        }
    }

    private void sendUserToHome()
    {
        BoardActivity.selfIntent.finish();

        Intent mainIntent = new Intent(CAuthActivity.this, CMainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void sendUserToWelcome()
    {
        BoardActivity.selfIntent.finish();

        Intent welcomeIntent = new Intent(CAuthActivity.this, COnboardScreenActivity.class);
        welcomeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(welcomeIntent);
        finish();
    }

    private void verificationPassed()
    {
        _otpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _phone = _otpUserNumber.getText().toString();
                _userName = _otpPassengerName.getText().toString();

                if(_phone.length() != 13)
                {
                    FancyToast.makeText(CAuthActivity.this, "Hakiki namba yako vizuri", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                }
                else
                {
                    _otpButton.setVisibility(View.INVISIBLE);
                    _progressBar.setVisibility(View.VISIBLE);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(_phone, 60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD, _callbacks);
                }
            }
        });

        _callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
            {
                String code = phoneAuthCredential.getSmsCode();

                if(code != null)
                {
                    _otpNumber.setVisibility(View.VISIBLE);
                    _otpNumber.setText(code);

                    signInWithPhoneAuthCredential(phoneAuthCredential);
                }
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e)
            {
                FancyToast.makeText(CAuthActivity.this, "Namba haijadhibitishwa. Jaribu tena", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                _progressBar.setVisibility(View.INVISIBLE);
                _otpButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCodeSent(@NonNull final String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
            {
                super.onCodeSent(s, forceResendingToken);
                _progressBar.setVisibility(View.INVISIBLE);
                _otpButton.setVisibility(View.VISIBLE);
                _otpNumber.setVisibility(View.VISIBLE);
                _otpUserNumber.setText(_phone);

                _otpButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(_otpNumber.getText().toString().isEmpty())
                        {
                            FancyToast.makeText(CAuthActivity.this, "Ingiza code ulizotumiwa", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
                        }
                        else
                        {
                            _otpButton.setVisibility(View.INVISIBLE);
                            _progressBar.setVisibility(View.VISIBLE);
                            PhoneAuthCredential _credential = PhoneAuthProvider.getCredential(s, _otpNumber.getText().toString());
                            signInWithPhoneAuthCredential(_credential);
                        }
                    }
                });

            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        _firebaseAuth.signInWithCredential(credential).addOnCompleteListener(CAuthActivity.this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {

                    FirebaseUser _currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    //adding user name to this authenticated user.
                    UserProfileChangeRequest _userUpdates = new UserProfileChangeRequest.Builder().setDisplayName(_userName).build();
                    Objects.requireNonNull(_currentUser).updateProfile(_userUpdates);
                    sendUserToWelcome();
                }
                else
                {
                    FancyToast.makeText(CAuthActivity.this, "Ingiza code ulizotumiwa", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
                }

                _progressBar.setVisibility(View.INVISIBLE);
                _otpButton.setVisibility(View.VISIBLE);
            }
        });
    }


}