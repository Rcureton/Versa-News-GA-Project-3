package com.adi.ho.jackie.versa_news;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.adi.ho.jackie.versa_news.Fragments.FashionFragment;
import com.adi.ho.jackie.versa_news.Fragments.FoodFragment;
import com.adi.ho.jackie.versa_news.Fragments.HomeFragment;
import com.adi.ho.jackie.versa_news.Fragments.NewsFragment;
import com.adi.ho.jackie.versa_news.Fragments.SportsFragment;
import com.adi.ho.jackie.versa_news.Fragments.TechFragment;
import com.adi.ho.jackie.versa_news.Fragments.TravelFragment;
import com.adi.ho.jackie.versa_news.ViewPagerAdapter.FragmentAdapter;
import com.antonyt.infiniteviewpager.InfinitePagerAdapter;
import com.antonyt.infiniteviewpager.InfiniteViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int horizontalChilds;
    private int verticalChilds;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private InfiniteViewPager viewPager;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        viewPager = (InfiniteViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        fillFragmentList();

        viewPager.setAdapter(new InfinitePagerAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment frag = null;
                    frag = fragmentList.get(position);
                    return frag;
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        }));

    }

    private void fillFragmentList(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new NewsFragment());
        fragmentList.add(new FashionFragment());
        fragmentList.add(new TechFragment());
        fragmentList.add(new SportsFragment());
        fragmentList.add(new FoodFragment());
        fragmentList.add(new TravelFragment());

    }

}
