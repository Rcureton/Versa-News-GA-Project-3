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
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.adi.ho.jackie.versa_news.Fragments.FashionFragment;
import com.adi.ho.jackie.versa_news.Fragments.FoodFragment;
import com.adi.ho.jackie.versa_news.Fragments.HomeFragment;
import com.adi.ho.jackie.versa_news.Fragments.MusicFragment;
import com.adi.ho.jackie.versa_news.Fragments.NewsFragment;
import com.adi.ho.jackie.versa_news.Fragments.PopularFragment;
import com.adi.ho.jackie.versa_news.Fragments.SportsFragment;
import com.adi.ho.jackie.versa_news.Fragments.TechFragment;
import com.adi.ho.jackie.versa_news.Fragments.TravelFragment;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceDataClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceItemsClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceSearchResultsClass;
import com.adi.ho.jackie.versa_news.Youtube.PlayVideos;
import com.google.gson.Gson;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
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
    ProgressDialog mProgress;

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
    private Window window;
    private Drawer mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tab_layout);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

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
                        Log.i("DRAWER","clicked on position: "+position);
                        if (position >= 4 && position <= 12) {
                            viewPager.setCurrentItem(position-4, true);
                            Log.i("DRAWER","clicked on position: "+position);
                            return true;

                        }
                        if (position == -1){
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
        mDrawer.addItemAtPosition(new PrimaryDrawerItem().withName(R.string.search).withIcon(android.R.drawable.ic_menu_search), 0);
        //modify an item of the drawer
        //item1.withName("A new name for this drawerItem").withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        //notify the drawer about the updated element. it will take care about everything else

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

        // Call async task that gets the API data and show that data in the view.
        DownloadPopularArticlesAsyncTask downloadPopularArticlesAsyncTask = new DownloadPopularArticlesAsyncTask();
        downloadPopularArticlesAsyncTask.execute(getMostPopularURL);

    }


    /* Change toolbarcolor
     *
     */
    @Override
    public void changeColor(Palette.Swatch light, Palette.Swatch dark) {
        if (light != null) {
            toolbar.setBackgroundColor(light.getRgb());
        }
        if (dark != null) {
            //TODO:Add support for lower versions if there is time
            window.setStatusBarColor(dark.getRgb());
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
            mDrawer.setSelectionAtPosition(position+4,false);
            if (popularFragment != null && popularFragment.popularImage != null) {
                Picasso.with(MainActivity.this).load(popularImageUrl).fit().into(popularFragment.popularImage);
            }

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

            colorAnimation.setDuration(1300);
            colorAnimation.setStartDelay(0);
            colorAnimation.start();



        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void fillColorArrays() {


        String[] colorStrings = {"#004D40",
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
    public static Account createSyncAccount(Context context) {
        Account newAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        AccountManager accountManager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);
//        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
//        } else {}

        return newAccount;
    }


    public void setImagesHomeFragment(ArrayList<String> imageUrls, ArrayList<String> headlineArray, ArrayList<String> previewArray) {

        Picasso.with(MainActivity.this).load(imageUrls.get(0)).fit().into(homeFragment.popularImage1);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(1)).fit().into(homeFragment.popularImage2);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(2)).fit().into(homeFragment.popularImage3);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(3)).fit().into(homeFragment.popularImage4);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(4)).fit().into(homeFragment.popularImage5);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(5)).fit().into(homeFragment.popularImage6);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(6)).fit().into(homeFragment.popularImage7);
        Picasso.with(homeFragment.getContext()).load(imageUrls.get(7)).fit().into(homeFragment.popularImage8);
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


}


