package com.tancorp.kibasi.managers.navigations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tancorp.kibasi.R;
import com.tancorp.kibasi.managers.adapters.MEViewPagerAdapter;

import java.util.Objects;

import static com.tancorp.kibasi.managers.MMainActivity._navigationView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MExploreFragment extends Fragment
{

    private TabLayout _tabLayout;
    private ViewPager _viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_m_explore, container, false);
        _navigationView.getMenu().getItem(0).setChecked(true);

        _tabLayout = _view.findViewById(R.id.m_explore_tab_layout);
        _viewPager = _view.findViewById(R.id.m_explore_view_pager);

        initViewPager();

        return _view;
    }

    private void initViewPager()
    {

        MEViewPagerAdapter _adapter = new MEViewPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), _tabLayout.getTabCount());
        _viewPager.setAdapter(_adapter);
        _tabLayout.setupWithViewPager(_viewPager);

        Objects.requireNonNull(_tabLayout.getTabAt(0)).setText("basi");
        Objects.requireNonNull(_tabLayout.getTabAt(1)).setText("ticket");
        Objects.requireNonNull(_tabLayout.getTabAt(1)).setIcon(R.drawable.ic_paid_dot_24dp);
        _tabLayout.setTabIconTint(getResources().getColorStateList(R.color.colorTransparentWhite));

        _viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(_tabLayout));
        _tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                _viewPager.setCurrentItem(tab.getPosition());
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

    @Override
    public void onResume()
    {
        super.onResume();
        initViewPager();
    }
}