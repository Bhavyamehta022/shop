package com.bhavya.shopfinderr;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.MenuInflater;
import android.widget.SeekBar;


public class PagerAdapter extends FragmentStatePagerAdapter {
    int NoOfTabs;
    public PagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.NoOfTabs=NumberOfTabs;
    }
    @Override
    public Fragment getItem(int p)
    {
        switch(p)
        {
            case 0:
                Home_Fragment home=new Home_Fragment();
                return home;
            case 1:
                Shops_Fragment shops_fragment=new Shops_Fragment();
                return shops_fragment;
            case 2:
                Products_Fragment products_fragment=new Products_Fragment();
                return products_fragment;
            case 3:
                Me_Fragment me_fragment=new Me_Fragment();
                return me_fragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return NoOfTabs;
    }
}




