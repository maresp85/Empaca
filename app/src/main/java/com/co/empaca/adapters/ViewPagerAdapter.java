package com.co.empaca.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.co.empaca.fragment.Fragment_Categories;
import com.co.empaca.fragment.NoseUsanTesting2;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[];
    int NumbOfTabs;

    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    @Override
    public Fragment getItem(int index) {
        if(index == 0) // if the position is 0 we are returning the First tab
        {
            Fragment_Categories fragment_categories = new Fragment_Categories();
            return fragment_categories;
        }else{
            NoseUsanTesting2 noseUsanTesting2 = new NoseUsanTesting2();
            return noseUsanTesting2;
        }
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

}