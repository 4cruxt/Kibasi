package com.tancorp.kibasi.managers.navigations;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.MRegisterBusActivity;
import com.tancorp.kibasi.managers.MRegisterMidActivity;
import com.tancorp.kibasi.managers.adapters.MPViewPagerAdapter;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MManagerFragment extends Fragment
{

    public static final int MMANAGER_FRAGMENT_ID = 2;
    public static int NUMBER_OF_BUSES;
    public static int NUMBER_OF_MIDS;

    private TabLayout _tabLayout;
    private ViewPager _viewPager;
    private FloatingActionButton _bus_floating_button;
    private FloatingActionButton _mid_floating_button;
    private MPViewPagerAdapter _adapter;

    private TextView _companyName;
    private TextView _busOwn;
    private TextView _companyMid;
    private FirebaseUser _currentuser;
    private FirebaseFirestore _firestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_m_manager, container, false);

        _tabLayout = _view.findViewById(R.id.m_p_tab_layout);
        _viewPager = _view.findViewById(R.id.m_p_view_pager);
        _bus_floating_button = _view.findViewById(R.id.m_p_bus_floating_button);
        _mid_floating_button = _view.findViewById(R.id.m_p_mid_floating_button);
        _companyName = _view.findViewById(R.id.m_p_company_name);
        _busOwn = _view.findViewById(R.id.m_p_bus_number);
        _companyMid = _view.findViewById(R.id.m_p_mid_number);

        FirebaseAuth _auth = FirebaseAuth.getInstance();
        _currentuser = _auth.getCurrentUser();
        _firestore = FirebaseFirestore.getInstance();

        initViewPager();
        setupFabButtons();
        setCustomData();
        updateData();

        return _view;
    }

    private void updateData()
    {
        _firestore.collection("BUSES").whereEqualTo("bus_owner", _currentuser.getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    int count = 0;
                    for(QueryDocumentSnapshot _documentSnapshot : Objects.requireNonNull(task.getResult()))
                    {
                        count++;
                    }
                    NUMBER_OF_BUSES = count;
                }
                else
                {
                    Log.d("FIREBASE ERROR", "Error getting documents: ", task.getException());
                }
            }
        });

        _firestore.collection("MIDS").whereEqualTo("bus_owner", _currentuser.getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    int count = 0;
                    for(QueryDocumentSnapshot _documentSnapshot : Objects.requireNonNull(task.getResult()))
                    {
                        count++;
                    }
                    NUMBER_OF_MIDS = count;
                }
            }
        });

        //todo: Remove this if condition in the new updates.
        if(_currentuser.getDisplayName() != null)
        {
            _companyName.setText(_currentuser.getDisplayName());
        }

    }

    @SuppressLint("SetTextI18n")
    private void setCustomData()
    {
        _busOwn.setText("" + NUMBER_OF_BUSES);
        _companyMid.setText("" + NUMBER_OF_MIDS);
    }


    private void setupFabButtons()
    {
        _bus_floating_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent registerBusIntent = new Intent(getContext(), MRegisterBusActivity.class);
                ActivityOptions _options = ActivityOptions.makeScaleUpAnimation(_bus_floating_button, 0, 0, _bus_floating_button.getWidth(), _bus_floating_button.getHeight());
                startActivity(registerBusIntent, _options.toBundle());
            }
        });

        _mid_floating_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent registerMidIntent = new Intent(getContext(), MRegisterMidActivity.class);
                ActivityOptions _options = ActivityOptions.makeScaleUpAnimation(_mid_floating_button, 0, 0, _mid_floating_button.getWidth(), _bus_floating_button.getHeight());
                startActivity(registerMidIntent, _options.toBundle());
            }
        });
    }

    private void initViewPager()
    {

        _adapter = new MPViewPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), _tabLayout.getTabCount());
        _viewPager.setAdapter(_adapter);
        _tabLayout.setupWithViewPager(_viewPager);

        Objects.requireNonNull(_tabLayout.getTabAt(0)).setText("basi");
        Objects.requireNonNull(_tabLayout.getTabAt(1)).setText("mhakiki");
        Objects.requireNonNull(_tabLayout.getTabAt(0)).setIcon(R.drawable.ic_bus_baseline_24);
        Objects.requireNonNull(_tabLayout.getTabAt(1)).setIcon(R.drawable.person_24dp);
        _tabLayout.setTabIconTint(getResources().getColorStateList(R.color.colorAccent));

        _viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(_tabLayout));
        _tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                _viewPager.setCurrentItem(tab.getPosition());
                animateFab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }

    private void animateFab(int position)
    {
        if(position == 1)
        {
            _bus_floating_button.hide(new FloatingActionButton.OnVisibilityChangedListener()
            {
                @Override
                public void onHidden(FloatingActionButton fab)
                {
                    super.onHidden(fab);
                    _mid_floating_button.show();
                }
            });
        }
        else
        {
            _mid_floating_button.hide(new FloatingActionButton.OnVisibilityChangedListener()
            {
                @Override
                public void onHidden(FloatingActionButton fab)
                {
                    super.onHidden(fab);
                    _bus_floating_button.show();
                }
            });
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        initViewPager();
        updateData();
    }

}