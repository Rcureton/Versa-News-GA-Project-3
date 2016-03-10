package com.adi.ho.jackie.versa_news;

import com.adi.ho.jackie.versa_news.Fragments.FashionFragment;


import android.app.ActionBar;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.adi.ho.jackie.versa_news.Fragments.MusicFragment;
import com.adi.ho.jackie.versa_news.Youtube.PlayVideos;


import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
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


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.adi.ho.jackie.versa_news.GSONClasses.ViceDataClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceItemsClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceSearchResultsClass;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String ARTICLEID = "ID";

    private int horizontalChilds;
    private int verticalChilds;
    private TabLayout tabLayout;
    private InfiniteViewPager viewPager;
    private List<Fragment> fragmentList;
    private List<ViceItemsClass> listViceArticles;
    private Bundle popularArticles;
    private ArrayList<String> urlArray;
    private ArrayList<String> headlineArray;
    private ArrayList<String> previewArray;
    private AppBarLayout appBarLayout;
    private boolean loadingFinished = false;
    private ArrayList<String> colorArray;
    private ArrayList<String> statusColorArray;


    DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);
        View headerLayout = nvDrawer.inflateHeaderView(R.layout.nav_header);
        mActivityTitle = getTitle().toString();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        setContentView(R.layout.tab_layout);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        viewPager = (InfiniteViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        appBarLayout = (AppBarLayout)findViewById(R.id.app_bar);




        listViceArticles = new ArrayList<>();
        urlArray = new ArrayList<>();
        headlineArray = new ArrayList<>();
        previewArray = new ArrayList<>();
        popularArticles = new Bundle();
        colorArray = new ArrayList<>();
        statusColorArray = new ArrayList<>();
        fillColorArrays();

        // Vice API URLs that data can be received through
        String getMostPopularURL = getResources().getString(R.string.get_most_popular);
        String getViceTodayURL = getResources().getString(R.string.get_vice_today);
        String getLatestURL = getResources().getString(R.string.get_latest);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // Call async task that gets the API data and show that data in the view.
        GetDataAsyncTask getDataAsyncTask = new GetDataAsyncTask();
        // TODO: Pass in the URL wanted, or create a variable that is updated based on the selected section.
        getDataAsyncTask.execute(getLatestURL);

      //  appBarLayout.addOnOffsetChangedListener(appBarOffsetListener);


    }
    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        Fragment fragment = null;

        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.home_drawer_fragment:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.news_drawer_fragment:
                fragmentClass = NewsFragment.class;
                break;
            case R.id.sports_drawer_fragment:
                fragmentClass = SportsFragment.class;
                break;
            case R.id.music_drawer_fragment:
                fragmentClass= MusicFragment.class;
                break;
            case R.id.fashion_drawer_fragment:
                fragmentClass= FashionFragment.class;
                break;
            case R.id.food_drawer_fragment:
                fragmentClass= FoodFragment.class;
                break;
            case R.id.tech_drawer_fragment:
                fragmentClass= TechFragment.class;
                break;
            case R.id.travel_drawer_fragment:
                fragmentClass= TravelFragment.class;
                break;

            default:
                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        // Highlight the selected item has been done by NavigationView
        // menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private class GetDataAsyncTask extends AsyncTask<String, Void, List<ViceItemsClass>> {
        @Override
        protected List<ViceItemsClass> doInBackground(String... myURL) {
            String data = "";
            try {
                URL url = new URL(myURL[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                data = getInputData(inputStream);
            } catch (Throwable e) {
                e.printStackTrace();
            }

            // Convert the JSON data to Gson data
            Gson gson = new Gson();
            ViceSearchResultsClass results = gson.fromJson(data, ViceSearchResultsClass.class);

            String articleID = results.getData().getItems().get(0).getId();
            Log.d("ASYNCTASK", "article id: " + results.getData().getItems().get(0).getId());
            Log.d("ASYNCTASK", results.getData().getItems().get(0).getTitle());

            /*
            To get the article ID, call: results.getData().getItems().get(0).getId();
            To get the category, call: results.getData().getItems().get(0).getCategory();
             */
            ViceDataClass item = results.getData();

            return item.getItems();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(List<ViceItemsClass> result) {
            listViceArticles = result;
            loadingFinished = true;
            for (int i = 0 ; i < 8; i++){
                urlArray.add(listViceArticles.get(i).getImage());
                headlineArray.add(listViceArticles.get(i).getTitle());
                previewArray.add(listViceArticles.get(i).getPreview());
            }
            popularArticles.putStringArrayList("POPULARURL", urlArray);
            popularArticles.putStringArrayList("POPULARHEADLINE", headlineArray);
            popularArticles.putStringArrayList("POPULARPREVIEW", previewArray);
            launchFragments();

        }

        public String getInputData(InputStream stream) throws IOException {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String read;

            while ((read = br.readLine()) != null) {
                sb.append(read);
            }

            br.close();
            return sb.toString();
        }


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

    private void launchFragments(){
        fillFragmentList();

        viewPager.setAdapter(new InfinitePagerAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment frag = null;

                frag = fragmentList.get(position);

                frag.setArguments(popularArticles);

                return frag;
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        }));

        viewPager.setCurrentItem(0);
       // viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //Color Animation
            position = position % 8;
            Integer colorFrom = Color.parseColor(colorArray.get(position-1));
            Integer colorTo = Color.parseColor(colorArray.get(position));
            Integer colorStatusFrom = Color.parseColor(statusColorArray.get(position-1));
            Integer colorStatusTo = Color.parseColor(statusColorArray.get(position));
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            ValueAnimator colorStatusAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorStatusFrom, colorStatusTo);

            colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    toolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
                }
            });

//            colorStatusAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//                @Override
//                public void onAnimationUpdate(ValueAnimator animator) {
//                    if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP) {
//                        getActivity().getWindow().setStatusBarColor((Integer) animator.getAnimatedValue());
//                    }
//                    if (currentapiVersion == Build.VERSION_CODES.KITKAT) {
//                        tintManager.setStatusBarTintColor((Integer) animator.getAnimatedValue());
//                    }
//                }
//            });
            colorAnimation.setDuration(1300);
            colorAnimation.setStartDelay(0);
            colorAnimation.start();
            colorStatusAnimation.setDuration(1300);
            colorStatusAnimation.setStartDelay(0);
            colorStatusAnimation.start();

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void fillColorArrays(){
        colorArray.add("#000000");
        colorArray.add("#aa0000");
        colorArray.add("#00aa00");
        colorArray.add("#0000aa");
        colorArray.add("#ff0000");
        colorArray.add("#00ff00");
        colorArray.add("#9900ff");
        colorArray.add("#ff00ff");

        statusColorArray.add("#000000");
        statusColorArray.add("#aa0000");
        statusColorArray.add("#00aa00");
        statusColorArray.add("#0000aa");
        statusColorArray.add("#ff0000");
        statusColorArray.add("#00ff00");
        statusColorArray.add("#9900ff");
        statusColorArray.add("#ff00ff");


    }



}
