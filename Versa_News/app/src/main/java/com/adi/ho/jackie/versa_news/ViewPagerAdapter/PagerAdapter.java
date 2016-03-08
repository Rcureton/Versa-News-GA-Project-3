package com.adi.ho.jackie.versa_news.ViewPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.adi.ho.jackie.versa_news.Fragments.FashionFragment;
import com.adi.ho.jackie.versa_news.Fragments.HomeFragment;
import com.adi.ho.jackie.versa_news.Fragments.SportsFragment;

/**
 * Created by JHADI on 3/8/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position){
            case 0 : new HomeFragment();
                break;
            case 1: new FashionFragment();
                break;
            case 2: new SportsFragment();
                break;
        }


        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = " ";

        return title;
    }
}
