package com.adi.ho.jackie.versa_news.ViewPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.adi.ho.jackie.versa_news.Fragments.HomeFragment;

/**
 * Created by JHADI on 3/8/16.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position){
            case 0: new HomeFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
