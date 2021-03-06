package com.adi.ho.jackie.versa_news.Youtube;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.adi.ho.jackie.versa_news.R;
import com.adi.ho.jackie.versa_news.RecyclerViewStuff.YoutubeRecyclerAdapter;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;

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


    // Your developer key goes here
    public static final String KEY
            = "AIzaSyCp4VCk-TmZPT82SmD_XISh4fx1oz8S_JE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_videos);

        videoRecycler = (RecyclerView)findViewById(R.id.player_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PlayVideos.this);
        videoRecycler.setHasFixedSize(true);
        videoRecycler.setLayoutManager(linearLayoutManager);

        mVideos= new ArrayList<>();

        LoadYoutubeTask load= new LoadYoutubeTask();
        load.execute();
//

        mVideosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("id",mVideos.get(position).getId());
                Intent intent = YouTubeStandalonePlayer.createPlaylistIntent(PlayVideos.this,
                        KEY, mVideos.get(position).getId());
                startActivity(intent);

//                Intent intent = new Intent(PlayVideos.this, PlayVideos.class);
//                intent.putExtra("video", mVideos.get(position).getId());
            }
        });


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



    public ArrayList<Playlist> getVideosList(){
        helper= new YoutubeHelper(PlayVideos.this);
        mPlayListArrayList = new ArrayList<>();

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

            mVideos.add(videoItem);
    }return mPlayListArrayList;

}


    public class LoadYoutubeTask extends AsyncTask<Void, Void, ArrayList<Playlist>> {


        @Override
        protected ArrayList<Playlist> doInBackground(Void... params) {
            return getVideosList();
        }

        @Override
        protected void onPostExecute(ArrayList<Playlist> playlists) {
            super.onPostExecute(playlists);
            YoutubeRecyclerAdapter youtubeAdapter = new YoutubeRecyclerAdapter(PlayVideos.this,mVideos);
            videoRecycler.setAdapter(youtubeAdapter);
        }
    }


}
