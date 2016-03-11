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

import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;


import android.os.Build;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;


import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.adi.ho.jackie.versa_news.Fragments.FashionFragment;


import com.adi.ho.jackie.versa_news.Fragments.FashionFragment;


import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CollapsingToolbarLayout;

import com.adi.ho.jackie.versa_news.Fragments.MusicFragment;
import com.adi.ho.jackie.versa_news.Fragments.PopularFragment;


import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.adi.ho.jackie.versa_news.Fragments.FoodFragment;
import com.adi.ho.jackie.versa_news.Fragments.HomeFragment;
import com.adi.ho.jackie.versa_news.Fragments.NewsFragment;
import com.adi.ho.jackie.versa_news.Fragments.SearchFragment;
import com.adi.ho.jackie.versa_news.Fragments.SportsFragment;
import com.adi.ho.jackie.versa_news.Fragments.TechFragment;
import com.adi.ho.jackie.versa_news.Fragments.TravelFragment;

import com.adi.ho.jackie.versa_news.Youtube.PlayVideos;
import com.antonyt.infiniteviewpager.InfinitePagerAdapter;
import com.antonyt.infiniteviewpager.InfiniteViewPager;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;


import com.adi.ho.jackie.versa_news.GSONClasses.ViceDataClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceItemsClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceSearchResultsClass;
import com.antonyt.infiniteviewpager.InfinitePagerAdapter;
import com.antonyt.infiniteviewpager.InfiniteViewPager;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.PuffOutAnimation;
import com.easyandroidanimations.library.SlideInUnderneathAnimation;
import com.google.gson.Gson;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
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


import java.util.Random;


public class MainActivity extends AppCompatActivity implements PopularFragment.ChangeToolbarColor {

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

    private int horizontalChilds;
    private int verticalChilds;
    private TabLayout tabLayout;
    public Toolbar toolbar;
    private ViewPager viewPager;
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

    private PopularFragment popularFragment;
    private String popularImageUrl;
    private String popularHeadline;
    private String popularPreview;
    private Window window;
    private Drawer mDrawer;
    private EditText mSearchEditText;
    private ImageView mSearchButton;
    private List<ViceItemsClass> alotOfArticles;
    private FrameLayout mToolbarContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tab_layout);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mSearchEditText = (EditText) findViewById(R.id.search);
        mSearchButton = (ImageView) findViewById(R.id.search__button_enter);
        mToolbarContainer = (FrameLayout)findViewById(R.id.toolbar_container);


        listViceArticles = new ArrayList<>();
        urlArray = new ArrayList<>();
        headlineArray = new ArrayList<>();
        idArray = new ArrayList<>();
        popularArticles = new Bundle();
        colorArray = new ArrayList<>();
        statusColorArray = new ArrayList<>();
        homeFragment = new HomeFragment();

        new DrawerBuilder().withActivity(this).build();
        // PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName(R.string.drawer_home);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName(R.string.drawer_sections).withSelectable(false);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withName(R.string.latest);
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withName(R.string.news);
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withName(R.string.fashion);
        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withName(R.string.tech);
        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withName(R.string.music);
        SecondaryDrawerItem item8 = new SecondaryDrawerItem().withName(R.string.sports);
        SecondaryDrawerItem item9 = new SecondaryDrawerItem().withName(R.string.food);
        SecondaryDrawerItem item10 = new SecondaryDrawerItem().withName(R.string.travel);
        SecondaryDrawerItem item11 = new SecondaryDrawerItem().withName(R.string.popular);


        //create the drawer and remember the `Drawer` result object
        mDrawer = new DrawerBuilder()
                .withActivity(MainActivity.this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(

                        new DividerDrawerItem(),
                        item2,
                        new DividerDrawerItem(),
                        item3, item4, item5, item6, item7, item8, item9, item10, item11

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Log.i("DRAWER", "clicked on position: " + position);
                        if (position >= 4 && position <= 12) {
                            viewPager.setCurrentItem(position - 4, true);
                            Log.i("DRAWER", "clicked on position: " + position);
                            mDrawer.closeDrawer();
                            return true;

                        }
                        if (position == -1) {
                            Intent youtubeIntent = new Intent(MainActivity.this, PlayVideos.class);
                            startActivity(youtubeIntent);
                            return true;
                        }
                        return false;
                        // do something with the clicked item :D
                    }
                })
                .build();

        mDrawer.addStickyFooterItemAtPosition((new PrimaryDrawerItem().withName(R.string.videos).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent youtubeIntent = new Intent(MainActivity.this, PlayVideos.class);
                startActivity(youtubeIntent);
                return true;
            }
        }).withIcon(android.R.drawable.ic_menu_upload_you_tube)), 0);
        mDrawer.addItemAtPosition(new PrimaryDrawerItem().withName(R.string.search).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                mSearchEditText.setVisibility(View.VISIBLE);
                mSearchButton.setVisibility(View.VISIBLE);
                mToolbarContainer.setVisibility(View.VISIBLE);
                new SlideInUnderneathAnimation(mSearchEditText).setDuration(500).animate();

                return true;
            }
        }).withIcon(android.R.drawable.ic_menu_search), 0);
        //modify an item of the drawer
        //item1.withName("A new name for this drawerItem").withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        //notify the drawer about the updated element. it will take care about everything else

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mSearchEditText.getText().toString().trim().isEmpty()) {
                    PuffOutAnimation puffOutAnimation = new PuffOutAnimation(mSearchEditText);
                    puffOutAnimation.setDuration(500).animate();
                    puffOutAnimation.setListener(new AnimationListener() {
                        @Override
                        public void onAnimationEnd(com.easyandroidanimations.library.Animation animation) {
                            SearchFragment searchFragment = new SearchFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("QUERY", mSearchEditText.getText().toString());
                            searchFragment.setArguments(bundle);
                            searchFragment.query = mSearchEditText.getText().toString();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            searchFragment.show(getFragmentManager(), mSearchEditText.getText().toString());
                        }
                    });
                }
              //  mSearchEditText.setText("");
                mToolbarContainer.setVisibility(View.GONE);
                mSearchEditText.setVisibility(View.GONE);
                mSearchButton.setVisibility(View.GONE);

            }
        });


        //Status bar setup
        window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        fillColorArrays();
        mAccount = createSyncAccount(this);
//        mProgress = new ProgressDialog(this);
//        mProgress.setMessage("Loading...");

        // Vice API URLs that data can be received through
        String getMostPopularURL = getResources().getString(R.string.get_most_popular);
        String getViceTodayURL = getResources().getString(R.string.get_vice_today);


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Call async task that gets the API data and show that data in the view.
            DownloadPopularArticlesAsyncTask downloadPopularArticlesAsyncTask = new DownloadPopularArticlesAsyncTask();
            downloadPopularArticlesAsyncTask.execute(getMostPopularURL);
        } else {
            Toast.makeText(MainActivity.this, "Please connect to the internet to refresh the feed", Toast.LENGTH_SHORT).show();
        }

    }


    /* Change toolbarcolor
     *
     */
    @Override
    public void changeColor(Palette.Swatch light, Palette.Swatch dark) {
        Integer colorFrom;
        Integer colorTo;
        Integer colorStatusFrom;
        Integer colorStatusTo;
        ColorDrawable toolbarColor = (ColorDrawable) toolbar.getBackground();
        if (light != null) {
             colorFrom = toolbarColor.getColor();
            colorTo = light.getRgb();
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    toolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
                }
            });
            colorAnimation.setDuration(1300);
            colorAnimation.setStartDelay(0);
            colorAnimation.start();
//            toolbar.setBackgroundColor(light.getRgb());

        }
        if (dark != null) {
            colorStatusFrom = window.getStatusBarColor();
            colorStatusTo = dark.getRgb();
            ValueAnimator colorStatusAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorStatusFrom, colorStatusTo);
            //TODO:Add support for lower versions if there is time
//            window.setStatusBarColor(dark.getRgb());
            colorStatusAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    window.setStatusBarColor((Integer) animation.getAnimatedValue());
                }
            });
            colorStatusAnimation.setDuration(1300);
            colorStatusAnimation.setStartDelay(0);
            colorStatusAnimation.start();
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
        }

        @Override
        protected void onPostExecute(List<ViceItemsClass> result) {
            /* Notifications */
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
            builder.setSmallIcon(android.R.drawable.ic_dialog_info);
            builder.setContentTitle("Vice Versa");
            builder.setContentText("New articles are now available.");

            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, (int) System.currentTimeMillis(), intent, 0);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);

            Notification notification = builder.build();
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(NOTIFICATION_ID, notification);

            listViceArticles = result;
            loadingFinished = true;
            for (int i = 0; i < 8; i++) {
                urlArray.add(listViceArticles.get(i).getImage());
                headlineArray.add(listViceArticles.get(i).getTitle());
                idArray.add(listViceArticles.get(i).getId());
            }
            popularArticles.putStringArrayList("POPULARURL", urlArray);
            popularArticles.putStringArrayList("POPULARHEADLINE", headlineArray);
            popularArticles.putStringArrayList("POPULARID", idArray);
            launchFragments();


            setImagesHomeFragment(urlArray, headlineArray, idArray);

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

    private class DownloadPopularArticlesAsyncTask extends AsyncTask<String, Void, List<ViceItemsClass>> {


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
            //  popularBundle = new Bundle();
            popularFragment = new PopularFragment();


            popularImageUrl = viceItemsClasses.get(0).getImage();
            popularHeadline = viceItemsClasses.get(0).getTitle();
            popularPreview = viceItemsClasses.get(0).getPreview();

            String getLatestURL = getResources().getString(R.string.get_latest);
            GetDataAsyncTask getDataAsyncTask = new GetDataAsyncTask();
            getDataAsyncTask.execute(getLatestURL);

        }
    }

    private void fillFragmentList() {
        fragmentList = new ArrayList<>();

        fragmentList.add(homeFragment);
        fragmentList.add(new NewsFragment());
        fragmentList.add(new FashionFragment());
        fragmentList.add(new TechFragment());
        fragmentList.add(new MusicFragment());
        fragmentList.add(new SportsFragment());
        fragmentList.add(new FoodFragment());
        fragmentList.add(new TravelFragment());
        fragmentList.add(popularFragment);


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


    private void launchFragments() {

        fillFragmentList();

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
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
        });

        viewPager.setPageTransformer(true, new DepthPageTransformer());// viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mDrawer.setSelectionAtPosition(position + 4, false);
            if (popularFragment != null && popularFragment.popularImage != null) {
                Picasso.with(MainActivity.this).load(popularImageUrl).fit().into(popularFragment.popularImage);
                popularFragment.popularHeadline.setText(popularHeadline);
                popularFragment.popularPreview.setText(popularPreview);
            }

            // Update title
            ArrayList<String> fragmentTitleList = new ArrayList<>();

            fragmentTitleList.add("Home"); // 0
            fragmentTitleList.add("News"); // 1
            fragmentTitleList.add("Fashion");  // 2
            fragmentTitleList.add("Tech"); // 3
            fragmentTitleList.add("Music"); // 4
            fragmentTitleList.add("Sports"); // 5
            fragmentTitleList.add("Food");  // 6
            fragmentTitleList.add("Travel"); // 7
            fragmentTitleList.add("Most Popular"); // 8

            setTitle(String.valueOf(fragmentTitleList.get(position)));

            //Color Animation
            ColorDrawable toolbarColor = (ColorDrawable) toolbar.getBackground();

            Integer colorFrom = toolbarColor.getColor();
            Integer colorTo = Color.parseColor(colorArray.get(position));
            Integer colorStatusFrom = window.getStatusBarColor();
            Integer colorStatusTo = Color.parseColor(statusColorArray.get(position));
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            ValueAnimator colorStatusAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorStatusFrom, colorStatusTo);

            colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    toolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
                }
            });
            colorStatusAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    window.setStatusBarColor((Integer) animation.getAnimatedValue());
                }
            });

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

    public void fillColorArrays() {


        String[] colorStrings = {"#FFC107",
                "#A1887F",
                "#FFE0B2",
                "#DCE775",
                "#C5E1A5",
                "#78909C",
                "#8C9EFF",
                "#FF8A80",
                "#BBDEFB",
        };
        colorArray.addAll(Arrays.asList(colorStrings));
        statusColorArray.add("#FF8F00");
        statusColorArray.add("#5D4037");
        statusColorArray.add("#FFB74D");
        statusColorArray.add("#C0CA33");
        statusColorArray.add("#7CB342");
        statusColorArray.add("#455A64");
        statusColorArray.add("#536DFE");
        statusColorArray.add("#FF5252");
        statusColorArray.add("#42A5F5");
    }


    // For authenticating an account.
    public static Account createSyncAccount(Context context) {
        Account newAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        AccountManager accountManager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);
//        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
//        } else {}

        return newAccount;
    }


    public void setImagesHomeFragment(ArrayList<String> imageUrls, ArrayList<String> headlineArray, ArrayList<String> previewArray) {
        try {
            Picasso.with(MainActivity.this).load(imageUrls.get(0)).fit().into(homeFragment.popularImage1);
            Picasso.with(homeFragment.getContext()).load(imageUrls.get(1)).fit().into(homeFragment.popularImage2);
            Picasso.with(homeFragment.getContext()).load(imageUrls.get(2)).fit().into(homeFragment.popularImage3);
            Picasso.with(homeFragment.getContext()).load(imageUrls.get(3)).fit().into(homeFragment.popularImage4);
            Picasso.with(homeFragment.getContext()).load(imageUrls.get(4)).fit().into(homeFragment.popularImage5);
            Picasso.with(homeFragment.getContext()).load(imageUrls.get(5)).fit().into(homeFragment.popularImage6);
            Picasso.with(homeFragment.getContext()).load(imageUrls.get(6)).fit().into(homeFragment.popularImage7);
            Picasso.with(homeFragment.getContext()).load(imageUrls.get(7)).fit().into(homeFragment.popularImage8);
        }catch (Throwable thr){

        }
    }

    public void setStatusBarBgColor(View statusBar, int color) {
        //status bar height
        int actionBarHeight = getActionBarHeight();
        int statusBarHeight = getStatusBarHeight();
        //action bar height
        statusBar.getLayoutParams().height = actionBarHeight + statusBarHeight;
        statusBar.setBackgroundColor(color);

    }

    public int getActionBarHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mSearchButton.setVisibility(View.GONE);
        mSearchEditText.setVisibility(View.GONE);
        mToolbarContainer.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSearchButton.setVisibility(View.GONE);
        mSearchEditText.setVisibility(View.GONE);
        mToolbarContainer.setVisibility(View.GONE);
    }

}