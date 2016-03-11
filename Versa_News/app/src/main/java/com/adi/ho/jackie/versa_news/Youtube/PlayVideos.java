package com.adi.ho.jackie.versa_news.Youtube;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.adi.ho.jackie.versa_news.MainActivity;
import com.adi.ho.jackie.versa_news.R;
import com.adi.ho.jackie.versa_news.RecyclerViewStuff.YoutubeRecyclerAdapter;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

public class PlayVideos extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    private YouTube youtube;
    private YouTube.Search.List query;
    private YouTubePlayerView playerView;
    private ArrayList<Playlist> mPlayListArrayList;
    public YoutubeHelper helper;

    ListView mVideosList;
    ArrayList<VideoItem> mVideos;
    private RecyclerView videoRecycler;
    private Window window;
    private Drawer mDrawer;
    public Toolbar toolbar;
    private ViewPager viewPager;

    // Your developer key goes here
    public static final String KEY
            = "AIzaSyCp4VCk-TmZPT82SmD_XISh4fx1oz8S_JE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_videos);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        videoRecycler = (RecyclerView)findViewById(R.id.player_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PlayVideos.this);
        videoRecycler.setHasFixedSize(true);
        videoRecycler.setLayoutManager(linearLayoutManager);
        setTitle("Vice on YouTube");

        mVideos= new ArrayList<>();

        LoadYoutubeTask load= new LoadYoutubeTask();
        load.execute();
//

//        mVideosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Log.d("id",mVideos.get(position).getId());
//                Intent intent = YouTubeStandalonePlayer.createPlaylistIntent(PlayVideos.this,
//                        KEY, mVideos.get(position).getId());
//                startActivity(intent);
//
////                Intent intent = new Intent(PlayVideos.this, PlayVideos.class);
////                intent.putExtra("video", mVideos.get(position).getId());
//            }
//        });

//        new DrawerBuilder().withActivity(this).build();
//        // PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName(R.string.drawer_home);
//        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName(R.string.drawer_sections).withSelectable(false);
//        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withName(R.string.latest);
//        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withName(R.string.news);
//        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withName(R.string.fashion);
//        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withName(R.string.tech);
//        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withName(R.string.music);
//        SecondaryDrawerItem item8 = new SecondaryDrawerItem().withName(R.string.sports);
//        SecondaryDrawerItem item9 = new SecondaryDrawerItem().withName(R.string.food);
//        SecondaryDrawerItem item10 = new SecondaryDrawerItem().withName(R.string.travel);
//        SecondaryDrawerItem item11 = new SecondaryDrawerItem().withName(R.string.popular);
//
//
//        //create the drawer and remember the `Drawer` result object
//        mDrawer = new DrawerBuilder()
//                .withActivity(PlayVideos.this)
//                .withToolbar(toolbar)
//                .withActionBarDrawerToggle(true)
//                .withActionBarDrawerToggleAnimated(true)
//                .addDrawerItems(
//
//                        new DividerDrawerItem(),
//                        item2,
//                        new DividerDrawerItem(),
//                        item3, item4, item5, item6, item7, item8, item9, item10, item11
//
//                )
//                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//                    @Override
//                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//                        Log.i("DRAWER","clicked on position: "+position);
//                        if (position >= 4 && position <= 12) {
//                            viewPager.setCurrentItem(position-4, true);
//                            Log.i("DRAWER","clicked on position: "+position);
//                            return true;
//
//                        }
//                        if (position == -1){
//                            Intent youtubeIntent = new Intent(PlayVideos.this, PlayVideos.class);
//                            startActivity(youtubeIntent);
//                            return true;
//                        }
//                        return false;
//                        // do something with the clicked item :D
//                    }
//                })
//                .build();
//
//        mDrawer.addStickyFooterItemAtPosition((new PrimaryDrawerItem().withName(R.string.videos).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//            @Override
//            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//                Intent youtubeIntent = new Intent(PlayVideos.this, PlayVideos.class);
//                startActivity(youtubeIntent);
//                return true;
//            }
//        }).withIcon(android.R.drawable.ic_menu_upload_you_tube)), 0);
//        mDrawer.addItemAtPosition(new PrimaryDrawerItem().withName(R.string.search).withIcon(android.R.drawable.ic_menu_search), 0);
        //modify an item of the drawer
        //item1.withName("A new name for this drawerItem").withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        //notify the drawer about the updated element. it will take care about everything else

//        //Status bar setup
//        window = getWindow();
//        // clear FLAG_TRANSLUCENT_STATUS flag:
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restored) {
        if(!restored){
            youTubePlayer.cueVideo(getIntent().getStringExtra("video"));
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show();
    }



    public ArrayList<VideoItem> getVideosList(){
        helper= new YoutubeHelper(PlayVideos.this);
        mPlayListArrayList = new ArrayList<>();
        ArrayList<VideoItem> videoList = new ArrayList<>();

        mPlayListArrayList = helper.getPlaylist();
        for (Playlist playlist : mPlayListArrayList) {
            VideoItem videoItem = new VideoItem();
            try{
                videoItem.setThumbnailURL(playlist.getSnippet().getThumbnails().getStandard().getUrl());
            }catch (Throwable throwable){
                videoItem.setThumbnailURL("http://images.fineartamerica.com/images-medium-large/2-nyc-empire-nina-papiorek.jpg");
            }
            videoItem.setId(playlist.getId());
            Log.d("Video",videoItem.getId());

            videoItem.setTitle(playlist.getSnippet().getTitle());
            videoItem.setDescription(playlist.getSnippet().getDescription());

            videoList.add(videoItem);
    }return videoList;

}


    public class LoadYoutubeTask extends AsyncTask<Void, Void, ArrayList<VideoItem>> {


        @Override
        protected ArrayList<VideoItem> doInBackground(Void... params) {
            return getVideosList();
        }

        @Override
        protected void onPostExecute(ArrayList<VideoItem> playlists) {
            mVideos = playlists;
            YoutubeRecyclerAdapter youtubeAdapter = new YoutubeRecyclerAdapter(PlayVideos.this,mVideos);
            videoRecycler.setAdapter(youtubeAdapter);
        }
    }


}
