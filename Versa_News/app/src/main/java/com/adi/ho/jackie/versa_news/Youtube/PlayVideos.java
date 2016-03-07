package com.adi.ho.jackie.versa_news.Youtube;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adi.ho.jackie.versa_news.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayVideos extends YouTubeBaseActivity {
    private YouTube youtube;
    private YouTube.Search.List query;
    private YouTubePlayerView playerView;
    public static YoutubeHelper helper;


    // Your developer key goes here
    public static final String KEY
            = "AIzaSyCp4VCk-TmZPT82SmD_XISh4fx1oz8S_JE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_videos);

        playerView = (YouTubePlayerView)findViewById(R.id.player_view);


        helper= new YoutubeHelper(this);
        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                Playlist playlist= helper.getPlaylist();
                // remove this line!
                Log.d("breakpoint", "breakpoint");
            }
        };
        Thread thread= new Thread(runnable);
        thread.start();



    }



}
