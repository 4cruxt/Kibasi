package com.tancorp.kibasi.managers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TimePicker;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.tancorp.kibasi.managers.MMainActivity.mmainIntent;
import static com.tancorp.kibasi.managers.navigations.MManagerFragment.MMANAGER_FRAGMENT_ID;

public class MRegisterBusActivity extends AppCompatActivity
{
    private int _requestCode = 100;
    private Activity selfIntent;

    private EditText _busName;
    private EditText _busSeatNumber;
    private EditText _busPlateNumber;
    private EditText _busFromRegion;
    private EditText _busToRegion;
    private EditText _busFromTime;
    private EditText _busToTime;
    private EditText _busPerTicket;
    private ImageView _busImage;
    private Button _submitButton;
    private Button _deleteButton;
    private ProgressBar _progressBar;
    private FirebaseFirestore _firestore;
    private FirebaseUser _currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_register_bus);
        selfIntent = this;

        _busName = findViewById(R.id.m_r_b_name);
        _busSeatNumber = findViewById(R.id.m_r_b_seat);
        _busPlateNumber = findViewById(R.id.m_r_b_plate_number);
        _busFromRegion = findViewById(R.id.m_r_b_from);
        _busToRegion = findViewById(R.id.m_r_b_to);
        _busFromTime = findViewById(R.id.m_r_b_from_time);
        _busToTime = findViewById(R.id.m_r_b_to_time);
        _busPerTicket = findViewById(R.id.m_r_b_ticket_payment);
        _busImage = findViewById(R.id.m_r_b_photo_upload);
        _submitButton = findViewById(R.id.m_register_bus_submit_button);
        _deleteButton = findViewById(R.id.m_r_b_delete_button);
        _progressBar = findViewById(R.id.m_r_b_progress_bar);

        _firestore = FirebaseFirestore.getInstance();
        FirebaseAuth _firebaseAuth = FirebaseAuth.getInstance();
        _currentUser = _firebaseAuth.getCurrentUser();

        timePickerDialog();
        imagePickerDialog();
        uploadData();
        deleteData();
    }

    private void deleteData()
    {
        _deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FancyToast.makeText(selfIntent, "Futa basi", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
            }
        });
    }

    private void uploadData()
    {
        _submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(_busName.length() == 0 || _busSeatNumber.length() == 0 || _busPlateNumber.length() == 0 || _busFromRegion.length() == 0 || _busToRegion.length() == 0 || _busFromTime.length() == 0 || _busToTime.length() == 0 || _busPerTicket.length() == 0)
                {
                    FancyToast.makeText(selfIntent, "Kamilisha taarifa", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                }
                else
                {
                    _submitButton.setVisibility(View.INVISIBLE);
                    _progressBar.setVisibility(View.VISIBLE);

                    dataList();
                }
            }
        });
    }

    //This method contains data to be added into the database

    private void dataList()
    {
        final Map<String, Object> _newBus = new HashMap<>();
        _newBus.put("bus_name", _busName.getText().toString());
        _newBus.put("bus_seat_number", _busSeatNumber.getText().toString());
        _newBus.put("bus_plate_number", _busPlateNumber.getText().toString());
        _newBus.put("bus_from_region", _busFromRegion.getText().toString().toLowerCase());
        _newBus.put("bus_to_region", _busToRegion.getText().toString().toLowerCase());
        _newBus.put("bus_from_time", _busFromTime.getText().toString());
        _newBus.put("bus_to_time", _busToTime.getText().toString());
        _newBus.put("bus_seat_per_ticket", _busPerTicket.getText().toString());
        _newBus.put("bus_owner", _currentUser.getEmail());

        //Add data to the database
        _firestore.collection("BUSES").document(Objects.requireNonNull(_newBus.get("bus_plate_number")).toString()).set(_newBus).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    FancyToast.makeText(selfIntent, _newBus.get("bus_name") + " limesajiliwa", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                    activateMainActivity();

                }
                else
                {
                    _progressBar.setVisibility(View.INVISIBLE);
                    _submitButton.setVisibility(View.VISIBLE);
                    FancyToast.makeText(selfIntent, _newBus.get("bus_name") + " halijasajiliwa", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                }
            }
        });

    }

    private void imagePickerDialog()
    {
        _busImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Pix.start((FragmentActivity) selfIntent, Options.init().setRequestCode(100).setExcludeVideos(true).setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode == _requestCode && data != null)
        {
            ///storage/emulated/0/DCIM/Screenshots/Screenshot_20200726-005751_Instagram.jpg
            ArrayList<String> photoPath = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            assert photoPath != null;
            String photo = photoPath.toString().replace("[", "").replace("]", "").replaceFirst("/", "");

            Bitmap _bitmap = BitmapFactory.decodeFile(photo);
            _busImage.setImageBitmap(_bitmap);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS)
        {//If request is cancelled, the result arrays are empty.
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

    private void timePickerDialog()
    {
        setupTimePicker(_busFromTime);
        setupTimePicker(_busToTime);
    }

    private void setupTimePicker(final EditText busTime)
    {
        busTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar _currentTime = Calendar.getInstance();
                int _hour = _currentTime.get(Calendar.HOUR_OF_DAY);
                int _minute = _currentTime.get(Calendar.MINUTE);

                TimePickerDialog _dialog;
                _dialog = new TimePickerDialog(MRegisterBusActivity.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        busTime.setText(hourOfDay + ":" + minute);
                    }
                }, _hour, _minute, true);
                _dialog.setTitle("Chagua Muda");
                _dialog.show();
            }
        });
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
    }
}