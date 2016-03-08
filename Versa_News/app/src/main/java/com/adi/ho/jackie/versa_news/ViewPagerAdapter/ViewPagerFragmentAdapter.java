package com.adi.ho.jackie.versa_news.ViewPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.adi.ho.jackie.versa_news.Fragments.FashionFragment;
import com.adi.ho.jackie.versa_news.Fragments.HomeFragment;
import com.adi.ho.jackie.versa_news.Fragments.SportsFragment;
import com.lsjwzh.widget.recyclerviewpager.FragmentStatePagerAdapter;
import com.lsjwzh.widget.recyclerviewpager.TabLayoutSupport;

/**
 * Created by JHADI on 3/8/16.
 */
public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {


    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i, Fragment.SavedState savedState) {
        Fragment frag = null;
        switch (i){
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
    public void onDestroyItem(int i, Fragment fragment) {

    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
