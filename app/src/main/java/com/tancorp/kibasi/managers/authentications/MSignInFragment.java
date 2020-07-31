package com.tancorp.kibasi.managers.authentications;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.tancorp.kibasi.BoardActivity;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.MMainActivity;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 **/

public class MSignInFragment extends Fragment
{
    public static final String INVALID_CREDENTIALS = "Umekosea anwani au neno la siri";

    private TextView _registerButtonText, _forgotPassword;
    private EditText _email, _password;
    private ProgressBar _progressBar;
    private FrameLayout _frameLayoutParent;
    private Button _signInButton;
    private String _emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private FirebaseAuth _firebaseAuth;

    public MSignInFragment()
    {
        //Required empty fragment constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_m_sign_in, container, false);

        _frameLayoutParent = Objects.requireNonNull(getActivity()).findViewById(R.id.auth_slider_container);
        _registerButtonText = _view.findViewById(R.id.m_in_register_button_text);
        _signInButton = _view.findViewById(R.id.m_in_submit_button);
        _forgotPassword = _view.findViewById(R.id.m_in_forgot_passwrd_button_text);
        _email = _view.findViewById(R.id.m_in_email_text);
        _password = _view.findViewById(R.id.m_in_password_text);
        _progressBar = _view.findViewById(R.id.m_in_progressbar);

        _firebaseAuth = FirebaseAuth.getInstance();

        return _view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        _registerButtonText.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v)
            {
                setFragment(new MSignUpFragment());
            }
        });

        _signInButton.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v)
            {
                checkEmailAndPassword();
            }
        });

        _forgotPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "Nimesahau neno la siri", Toast.LENGTH_SHORT).show();
            }
        });

        _email.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        _password.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }

    private void checkInputs()
    {
        if(!TextUtils.isEmpty(_email.getText()))
        {
            if(!TextUtils.isEmpty(_password.getText()))
            {
                enableButton();
            }
            else
            {
                disableButton();
            }
        }
        else
        {
            disableButton();
        }

    }

    private void disableButton()
    {
        _signInButton.setEnabled(false);
    }

    private void enableButton()
    {
        _signInButton.setEnabled(true);
    }

    private void checkEmailAndPassword()
    {
        if(_email.getText().toString().matches(_emailPattern))
        {
            if(_password.length() >= 8)
            {
                _signInButton.setVisibility(View.INVISIBLE);
                _progressBar.setVisibility(View.VISIBLE);

                _firebaseAuth.signInWithEmailAndPassword(_email.getText().toString(), _password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            mainIntent();
                        }
                        else
                        {
                            _progressBar.setVisibility(View.INVISIBLE);
                            _signInButton.setVisibility(View.VISIBLE);
                            FancyToast.makeText(getContext(), "Hakuna mmiliki mwenye taarifa hizi", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();

                        }
                    }
                });
            }
            else
            {
                FancyToast.makeText(getActivity(), INVALID_CREDENTIALS, FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
            }
        }
        else
        {
            FancyToast.makeText(getActivity(), INVALID_CREDENTIALS, FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
    }

    private void mainIntent()
    {
        BoardActivity.selfIntent.finish();
        Intent mainIntent = new Intent(getContext(), MMainActivity.class);
        startActivity(mainIntent);
        Objects.requireNonNull(getActivity()).finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction _transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        _transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_out_from_left);
        _transaction.replace(_frameLayoutParent.getId(), fragment);
        _transaction.commit();
    }


}