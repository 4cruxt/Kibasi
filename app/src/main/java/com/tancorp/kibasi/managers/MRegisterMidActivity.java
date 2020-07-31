package com.tancorp.kibasi.managers;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.fxn.pix.Options;
import com.fxn.pix.Pix;
import com.fxn.utility.PermUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.tancorp.kibasi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.tancorp.kibasi.managers.MMainActivity.mmainIntent;
import static com.tancorp.kibasi.managers.navigations.MManagerFragment.MMANAGER_FRAGMENT_ID;

public class MRegisterMidActivity extends AppCompatActivity
{

    private Activity thisIntent;
    private int _requestCode = 100;

    private TextView _midName;
    private TextView _midBusGiven;
    private TextView _midPhoneNumber;
    private ImageView _midPhoto;
    private Button _submitButton;
    private ProgressBar _progressBar;
    private String _lastChar = " ";
    private FirebaseUser _currentUser;
    private FirebaseFirestore _firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_register_mid);
        thisIntent = this;

        _midName = findViewById(R.id.m_r_mid_name);
        _midBusGiven = findViewById(R.id.m_r_mid_bus_given);
        _midPhoneNumber = findViewById(R.id.m_r_mid_phone_number);
        _midPhoto = findViewById(R.id.m_r_mid_photo_upload);
        _submitButton = findViewById(R.id.m_register_mid_submit_button);
        _progressBar = findViewById(R.id.m_r_mid_progressbar);

        FirebaseAuth _auth = FirebaseAuth.getInstance();
        _currentUser = _auth.getCurrentUser();
        _firestore = FirebaseFirestore.getInstance();

        imagePickerDialog();
        uploadData();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        activateMainActivity();
    }

    private void activateMainActivity()
    {
        mmainIntent.finish();
        Intent busIntent = new Intent(this, MMainActivity.class);
        busIntent.putExtra("Fragment_id", MMANAGER_FRAGMENT_ID);
        ActivityOptions _options = ActivityOptions.makeScaleUpAnimation(_submitButton, 0, 0, _submitButton.getWidth(), _submitButton.getHeight());
        startActivity(busIntent, _options.toBundle());
        finish();
    }

    private void securityChecker()
    {
        _midPhoneNumber.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                int _digits = _midPhoneNumber.getText().toString().length();
                if(_digits > 1)
                {
                    _lastChar = _midPhoneNumber.getText().toString().substring(_digits - 1);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                int digits = _midPhoneNumber.getText().toString().length();
                if(!_lastChar.equals("-"))
                {
                    if(digits == 3 || digits == 7)
                    {
                        _midPhoneNumber.append("-");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }

    private void uploadData()
    {
        securityChecker();

        _submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(_midName.length() == 0 || _midBusGiven.length() == 0 || _midPhoneNumber.length() == 0)
                {
                    FancyToast.makeText(thisIntent, "Kamilisha taarifa", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                }
                else
                {
                    _submitButton.setVisibility(View.INVISIBLE);
                    _progressBar.setVisibility(View.VISIBLE);
                    dataList();
                    activateMainActivity();
                }
            }
        });

    }

    //This method contains data to be added into the database
    private void dataList()
    {
        final Map<String, Object> _dataList = new HashMap<>();
        _dataList.put("mid_name", _midName.getText().toString());
        _dataList.put("mid_bus_given", _midBusGiven.getText().toString());
        _dataList.put("mid_phone_number", _midPhoneNumber.getText().toString());
        _dataList.put("bus_owner", _currentUser.getEmail());


        //Add data to firestore database
        _firestore.collection("MIDS").document(Objects.requireNonNull(_dataList.get("mid_bus_given")).toString()).set(_dataList).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    _progressBar.setVisibility(View.INVISIBLE);
                    _submitButton.setVisibility(View.VISIBLE);
                    FancyToast.makeText(thisIntent, Objects.requireNonNull(_dataList.get("mid_name")).toString() + " Amesajiliwa", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                }
                else
                {
                    _submitButton.setVisibility(View.INVISIBLE);
                    _progressBar.setVisibility(View.VISIBLE);
                    FancyToast.makeText(thisIntent, Objects.requireNonNull(_dataList.get("mid_name")).toString() + " Hasajiliwa", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();

                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode == _requestCode && data != null)
        {
            ArrayList<String> photoPath = data.getStringArrayListExtra(Pix.IMAGE_RESULTS); //Produces the results for the selected image in the pop up menu.

            assert photoPath != null;
            String photo = photoPath.toString().replace("[", "").replace("]", "").replaceFirst("/", "");

            Bitmap _bitmap = BitmapFactory.decodeFile(photo);
            _midPhoto.setImageBitmap(_bitmap);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS)
        {
            //If request is cancelled, the result arrays are empty.
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                Pix.start(this, _requestCode);
            }
            else
            {
                Toast.makeText(this, "Ruhusu Camera kutumika", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void imagePickerDialog()
    {
        _midPhoto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Pix.start((FragmentActivity) thisIntent, Options.init().setRequestCode(100).setExcludeVideos(true).setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT));
            }
        });

    }


}