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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.tancorp.kibasi.BoardActivity;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.MOnboardScreenActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MSignUpFragment extends Fragment
{
    private TextView _signUpButtonText;
    private EditText _companyName, _emailAddress, _password, _confirmPassword;
    private ProgressBar _progressBar;
    private FrameLayout _framLayoutParent;
    private Button _signUpButton;
    private FirebaseAuth _firebaseAuth;
    private FirebaseFirestore _firebaseFirestore;
    private String _emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    public MSignUpFragment()
    {
        //Required empty public fragment constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_m_sign_up, container, false);

        _signUpButtonText = _view.findViewById(R.id.m_up_signin_button_text);
        _signUpButton = _view.findViewById(R.id.m_up_submit_button);
        _progressBar = _view.findViewById(R.id.m_up_progressbar);
        _companyName = _view.findViewById(R.id.m_up_name_text);
        _emailAddress = _view.findViewById(R.id.m_up_email_text);
        _password = _view.findViewById(R.id.m_up_passwrd_text);
        _confirmPassword = _view.findViewById(R.id.m_up_passwrd_confirm_text);
        _framLayoutParent = Objects.requireNonNull(getActivity()).findViewById(R.id.auth_slider_container);

        _firebaseAuth = FirebaseAuth.getInstance();
        _firebaseFirestore = FirebaseFirestore.getInstance();

        return _view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        _signUpButtonText.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v)
            {
                setFragment(new MSignInFragment());
            }
        });

        _signUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkEmailAndPassword();
            }
        });

        _companyName.addTextChangedListener(new TextWatcher()
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

        _emailAddress.addTextChangedListener(new TextWatcher()
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

        _confirmPassword.addTextChangedListener(new TextWatcher()
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

    private void checkEmailAndPassword()
    {
        if(_emailAddress.getText().toString().matches(_emailPattern))
        {
            if(_password.getText().toString().equals(_confirmPassword.getText().toString()))
            {
                _signUpButton.setVisibility(View.INVISIBLE);
                _progressBar.setVisibility(View.VISIBLE);

                //Pass authentication information to the firebase database.

                _firebaseAuth.createUserWithEmailAndPassword(_emailAddress.getText().toString(), _password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {

                            //Adding username to authentication path
                            FirebaseUser _user = _firebaseAuth.getCurrentUser();
                            UserProfileChangeRequest _profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(_companyName.getText().toString()).build();
                            Objects.requireNonNull(_user).updateProfile(_profileUpdates);

                            saveUserData();
                        }
                        else
                        {
                            _progressBar.setVisibility(View.INVISIBLE);
                            _signUpButton.setVisibility(View.VISIBLE);
                            enableButton();
                            FancyToast.makeText(getContext(), "Anwani hii imeshatumika na mmliki mwingine", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }
                    }
                });
            }
            else
            {
                _confirmPassword.setError("Neno la siri halifanani");
            }
        }
        else
        {
            _emailAddress.setError("Anwani si sahihi");
        }
    }

    private void saveUserData()
    {
        Map<Object, String> _userData = new HashMap<>();
        _userData.put("company_name", _companyName.getText().toString());

        //Implement firebase firestore to store company's name into the firestore database.
        _firebaseFirestore.collection("COMPANIES").add(_userData).addOnCompleteListener(new OnCompleteListener<DocumentReference>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task)
            {
                if(task.isSuccessful())
                {
                    mainIntent();
                }
                else
                {
                    _progressBar.setVisibility(View.INVISIBLE);
                    _signUpButton.setVisibility(View.VISIBLE);
                    enableButton();
                    String error = Objects.requireNonNull(task.getException()).getMessage();
                    FancyToast.makeText(getContext(), error, FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                }
            }
        });

    }

    private void mainIntent()
    {
        BoardActivity.selfIntent.finish();
        Intent onBoardIntent = new Intent(getContext(), MOnboardScreenActivity.class);
        Objects.requireNonNull(getContext()).startActivity(onBoardIntent);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void checkInputs()
    {
        if(!TextUtils.isEmpty(_companyName.getText()))
        {
            if(!TextUtils.isEmpty(_emailAddress.getText()))
            {
                if(!TextUtils.isEmpty(_password.getText()) && _password.length() >= 8)
                {
                    if(!TextUtils.isEmpty(_confirmPassword.getText()))
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
        _signUpButton.setEnabled(false);
    }

    private void enableButton()
    {
        _signUpButton.setEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction _transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        _transaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slide_out_from_right);
        _transaction.replace(_framLayoutParent.getId(), fragment);
        _transaction.commit();
    }
}