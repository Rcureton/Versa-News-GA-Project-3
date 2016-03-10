package com.adi.ho.jackie.versa_news;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.adi.ho.jackie.versa_news.Fragments.FashionFragment;
import com.adi.ho.jackie.versa_news.Fragments.FoodFragment;
import com.adi.ho.jackie.versa_news.Fragments.HomeFragment;
import com.adi.ho.jackie.versa_news.Fragments.NewsFragment;
import com.adi.ho.jackie.versa_news.Fragments.SportsFragment;
import com.adi.ho.jackie.versa_news.Fragments.TechFragment;
import com.adi.ho.jackie.versa_news.Fragments.TravelFragment;
import com.adi.ho.jackie.versa_news.Fragments.VideoFragment;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceDataClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceItemsClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceSearchResultsClass;
import com.antonyt.infiniteviewpager.InfinitePagerAdapter;
import com.antonyt.infiniteviewpager.InfiniteViewPager;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    Button mChangeActivityButton;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;

    // These could be stored as string values instead.
    private static final String AUTHORITY = "com.adi.ho.jackie.versa_news.ViceContentProvider";
    private static final String ACCOUNT_TYPE = "example.com";
    private static final String ACCOUNT = "default_account";
    public static final String ARTICLEID = "ID";
    public static int NOTIFICATION_ID = 1;
    NotificationCompat.Builder builder;
    Account mAccount;
    ContentResolver mResolver;
    ProgressDialog mProgress;

    private int horizontalChilds;
    private int verticalChilds;
    private TabLayout tabLayout;
    public Toolbar toolbar;
    private InfiniteViewPager viewPager;
    private List<Fragment> fragmentList;
    public List<ViceItemsClass> listViceArticles;
    private Bundle popularArticles;
    private ArrayList<String> urlArray;
    private ArrayList<String> headlineArray;
    private ArrayList<String> idArray;
    private AppBarLayout appBarLayout;
    private boolean loadingFinished = false;
    private ArrayList<String> colorArray;
    private ArrayList<String> statusColorArray;

    private HomeFragment homeFragment;
    private CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.tab_layout);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        viewPager = (InfiniteViewPager) findViewById(R.id.viewpager);

        //tabLayout = (TabLayout) findViewById(R.id.tabs);
        //appBarLayout = (AppBarLayout)findViewById(R.id.app_bar);
        //toolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);

        listViceArticles = new ArrayList<>();
        urlArray = new ArrayList<>();
        headlineArray = new ArrayList<>();
        idArray = new ArrayList<>();
        popularArticles = new Bundle();
        colorArray = new ArrayList<>();
        statusColorArray = new ArrayList<>();
        homeFragment = new HomeFragment();
        fillColorArrays();
        mAccount = createSyncAccount(this);
//        mProgress = new ProgressDialog(this);
//        mProgress.setMessage("Loading...");

     //   appBarLayout.setBackgroundColor(Color.parseColor(colorArray.get(3)));
        // Vice API URLs that data can be received through
        String getMostPopularURL = getResources().getString(R.string.get_most_popular);
        String getViceTodayURL = getResources().getString(R.string.get_vice_today);


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // Call async task that gets the API data and show that data in the view.
        DownloadPopularArticlesAsyncTask downloadPopularArticlesAsyncTask = new DownloadPopularArticlesAsyncTask();
        downloadPopularArticlesAsyncTask.execute(getMostPopularURL);

        //  appBarLayout.addOnOffsetChangedListener(appBarOffsetListener);

        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[3];

        /* For calling the sync adapter to refresh manually -- currently crashes the app. */
//        mResolver = getContentResolver();
//        Bundle settingsBundle = new Bundle();
//        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
//        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
//        mResolver.requestSync(mAccount, AUTHORITY, settingsBundle);
//        Toast.makeText(MainActivity.this, "Syncing...", Toast.LENGTH_SHORT).show();

//        autoSyncData();

//        drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_action_calendar_day, "Latest");
//        drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_star, "Hot");
//        drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_today, "Loaded");


//        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        //  mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
//        mDrawerList = (ListView)findViewById(R.id.left_drawer);

        // DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);
        // mDrawerList.setAdapter(adapter);


        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mTitle = mDrawerTitle = getTitle();

//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(
//                this,
//                mDrawerLayout,
//                toolbar,
//                R.string.drawer_open,
//                R.string.drawer_close
//        )
        {

            /** Called when a drawer has settled in a completely closed state. */
//            public void onDrawerClosed(View view) {
//                super.onDrawerClosed(view);
//                getSupportActionBar().setTitle(mTitle);
//            }
//
//            /** Called when a drawer has settled in a completely open state. */
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                getSupportActionBar().setTitle(mDrawerTitle);
//            }
//        };

            mDrawerLayout.setDrawerListener(mDrawerToggle);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }
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
//            mProgress.show();
        }

        @Override
        protected void onPostExecute(List<ViceItemsClass> result) {
            /* Notifications */
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
            // TODO: Replace setSmallIcon with our app icon.
            builder.setSmallIcon(android.R.drawable.ic_dialog_info);
            builder.setContentTitle("Vice Versa");
            builder.setContentText("New articles are now available.");

            Intent intent= new Intent(MainActivity.this,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, (int) System.currentTimeMillis(), intent, 0);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);

            Notification notification= builder.build();
            NotificationManager manager= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            manager.notify(NOTIFICATION_ID, notification);

            listViceArticles = result;
            loadingFinished = true;
            for (int i = 0 ; i < 8; i++){
                urlArray.add(listViceArticles.get(i).getImage());
                headlineArray.add(listViceArticles.get(i).getTitle());
                idArray.add(listViceArticles.get(i).getId());
            }
            popularArticles.putStringArrayList("POPULARURL", urlArray);
            popularArticles.putStringArrayList("POPULARHEADLINE", headlineArray);
            popularArticles.putStringArrayList("POPULARID", idArray);
            launchFragments();

            setImagesHomeFragment(urlArray,headlineArray,idArray);
            //Set images from home fragment

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

    private class DownloadPopularArticlesAsyncTask extends AsyncTask<String,Void,List<ViceItemsClass>>{


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

            /*
            To get the article ID, call: results.getData().getItems().get(0).getId();
            To get the category, call: results.getData().getItems().get(0).getCategory();
             */
            ViceDataClass item = results.getData();

            return item.getItems();
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

        @Override
        protected void onPostExecute(List<ViceItemsClass> viceItemsClasses) {
            String getLatestURL = getResources().getString(R.string.get_latest);
            GetDataAsyncTask getDataAsyncTask = new GetDataAsyncTask();
            getDataAsyncTask.execute(getLatestURL);

        }
    }

    private void fillFragmentList(){
        fragmentList = new ArrayList<>();
        fragmentList.add(homeFragment);
        fragmentList.add(new NewsFragment());
        fragmentList.add(new FashionFragment());
        fragmentList.add(new TechFragment());
        fragmentList.add(new SportsFragment());
        fragmentList.add(new FoodFragment());
        fragmentList.add(new TravelFragment());
        fragmentList.add(new VideoFragment());

    }

    /* Call this method to enable periodic refresh of articles. Currently logs live data,
    * but does not connect to the home screen view without crashing.
    */

 /*   public void autoSyncData(){
        // TODO: Add a settings option to disable automatic syncing.
        // Periodically refresh latest stories
        ContentResolver.setSyncAutomatically(mAccount, AUTHORITY, true);
        ContentResolver.addPeriodicSync(
                mAccount,
                AUTHORITY,
                Bundle.EMPTY,
                //21600); // To update every 6 hours
                10);

        Toast.makeText(MainActivity.this, "Syncing...", Toast.LENGTH_SHORT).show();
       List<ViceItemsClass> result = new ArrayList<>();
        listViceArticles = result;
        loadingFinished = true;

        for (int i = 0 ; i < 8; i++){
            urlArray.add(listViceArticles.get(i).getImage());
            headlineArray.add(listViceArticles.get(i).getTitle());
            previewArray.add(listViceArticles.get(i).getPreview());
            Log.d("AUTOSYNCDATA_FOR", headlineArray.get(i));
        }
        popularArticles.putStringArrayList("POPULARURL", urlArray);
        popularArticles.putStringArrayList("POPULARHEADLINE", headlineArray);
        popularArticles.putStringArrayList("POPULARPREVIEW", previewArray);
        launchFragments();
        Log.d("AUTOSYNCDATA_LAUNCH", "Sync complete.");
        }
*/

    @Override
    protected void onResume() {
        super.onResume();
        // Clear notification when app is opened.
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
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

        viewPager.setPageTransformer(true, new DepthPageTransformer());// viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //Color Animation
            Random rand = new Random();
            position = rand.nextInt(19);
            ColorDrawable toolbarColor = (ColorDrawable) toolbar.getBackground();

            Integer colorFrom = toolbarColor.getColor();
            Integer colorTo = Color.parseColor(colorArray.get(position));
           // Integer colorStatusFrom = getS
            //Integer colorStatusTo = Color.parseColor(statusColorArray.get(position));
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            //ValueAnimator colorStatusAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorStatusFrom, colorStatusTo);

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
//            colorStatusAnimation.setDuration(1300);
//            colorStatusAnimation.setStartDelay(0);
//            colorStatusAnimation.start();

           // MainActivity.this.setTheme(R.style.ToolbarTheme1);


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void fillColorArrays(){


        String[] colorStrings = { "#004D40",
                "#00695C",
                "#00796B",
                "#00897B",
                "#009688",
                "#26A69A",
                "#4DB6AC",
                "#80CBC4",
                "#B2DFDB",
                "#1B5E20",
                "#2E7D32",
                "#388E3C",
                "#43A047",
                "#4CAF50",
                "#66BB6A",
                "#81C784",
                "#A5D6A7",
                "#C8E6C9",
                "#E8F5E9"};
       colorArray.addAll(Arrays.asList(colorStrings));

        statusColorArray.add("#000000");
        statusColorArray.add("#aa0000");
        statusColorArray.add("#00aa00");
        statusColorArray.add("#0000aa");
        statusColorArray.add("#ff0000");
        statusColorArray.add("#00ff00");
        statusColorArray.add("#9900ff");
        statusColorArray.add("#ff00ff");


    }

    // For authenticating an account.
    public static Account createSyncAccount(Context context){
        Account newAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        AccountManager accountManager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);
//        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
//        } else {}

        return newAccount;
    }

    public void setImagesHomeFragment(ArrayList<String> imageUrls, ArrayList<String> headlineArray, ArrayList<String> previewArray){
        Picasso.with(MainActivity.this).load(imageUrls.get(0)).fit().into(homeFragment.popularImage1);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(1)).fit().into(homeFragment.popularImage2);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(2)).fit().into(homeFragment.popularImage3);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(3)).fit().into(homeFragment.popularImage4);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(4)).fit().into(homeFragment.popularImage5);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(5)).fit().into(homeFragment.popularImage6);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(6)).fit().into(homeFragment.popularImage7);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(7)).fit().into(homeFragment.popularImage8);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        private android.support.v4.app.FragmentManager fragmentManager;

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
             //   fragment = new CreateFragment();
                break;
            case 1:
               // fragment = new ReadFragment();
                break;
            case 2:
                //fragment = new ReadTop();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
           // fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
}


