package com.tancorp.kibasi.managers.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.tancorp.kibasi.managers.profile.MPBusPagerFragment;
import com.tancorp.kibasi.managers.profile.MPMidPagerFragment;

public class MPViewPagerAdapter extends FragmentStatePagerAdapter
{
    private int _tabNumber;

    public MPViewPagerAdapter(@NonNull FragmentManager fm, int tabNumber)
    {
        super(fm);
        _tabNumber = tabNumber;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                return new MPBusPagerFragment();
            case 1:
                return new MPMidPagerFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return _tabNumber;
    }
}
