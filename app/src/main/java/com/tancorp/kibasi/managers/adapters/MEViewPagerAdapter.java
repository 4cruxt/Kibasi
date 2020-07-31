package com.tancorp.kibasi.managers.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.tancorp.kibasi.managers.explore.MEBusPagerFragment;
import com.tancorp.kibasi.managers.explore.METicketPagerFragment;

public class MEViewPagerAdapter extends FragmentStatePagerAdapter
{
    private int _tabNumber;

    public MEViewPagerAdapter(@NonNull FragmentManager fm, int tabNumber)
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
                return new MEBusPagerFragment();
            case 1:
                return new METicketPagerFragment();
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
