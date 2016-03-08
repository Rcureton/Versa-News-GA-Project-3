package com.adi.ho.jackie.versa_news;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.adi.ho.jackie.versa_news.LayoutFiles.VerticalPagerAdapter;
import com.adi.ho.jackie.versa_news.RecyclerViewStuff.ArticleRecyclerAdapter;
import com.adi.ho.jackie.versa_news.RecyclerViewStuff.ArticleViewHolder;
import com.adi.ho.jackie.versa_news.ViewPagerAdapter.PagerAdapter;
import com.adi.ho.jackie.versa_news.ViewPagerAdapter.ViewPagerFragmentAdapter;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int horizontalChilds;
    private int verticalChilds;
    private TabLayout tabLayout;
    private ViewPagerFragmentAdapter mAdapter;
    private Toolbar toolbar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);
//        loadDataFromSplash();
//        loadUI();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //back button

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        FragmentManager fragmentManager = getSupportFragmentManager();
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTabsFromPagerAdapter(pagerAdapter);

    }

}
