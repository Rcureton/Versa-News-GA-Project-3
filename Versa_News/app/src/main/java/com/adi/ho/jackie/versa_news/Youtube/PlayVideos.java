package com.adi.ho.jackie.versa_news.Youtube;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.adi.ho.jackie.versa_news.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;

import java.util.ArrayList;

public class PlayVideos extends YouTubeBaseActivity {
    private YouTube youtube;
    private YouTube.Search.List query;
    private YouTubePlayerView playerView;
    private ArrayList<Playlist> mPlayListArrayList;
    public YoutubeHelper helper;

    ListView mVideosList;
    ArrayList<VideoItem> mVideos;




    // Your developer key goes here
    public static final String KEY
            = "AIzaSyCp4VCk-TmZPT82SmD_XISh4fx1oz8S_JE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_videos);

        mVideosList=(ListView)findViewById(R.id.listView);
        playerView = (YouTubePlayerView)findViewById(R.id.player_view);
        mVideos= new ArrayList<>();

        LoadYoutubeTask load= new LoadYoutubeTask();
        load.execute();

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

            YoutubeAdapter adapter= new YoutubeAdapter(PlayVideos.this,mVideos);
            mVideosList.setAdapter(adapter);
        }
    }


}
