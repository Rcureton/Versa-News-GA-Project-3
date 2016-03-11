package com.adi.ho.jackie.versa_news.Youtube;

import android.content.Context;
import android.net.Credentials;
import android.os.AsyncTask;

import com.adi.ho.jackie.versa_news.R;
import com.google.android.gms.auth.api.*;
import com.google.api.client.auth.oauth2.Credential;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ra on 3/7/16.
 */
public class YoutubeHelper {
    YouTube youTube;
    private ArrayList<Playlist> mPlayListArray;

    public YoutubeHelper(Context context){

        List<String> scopes= Lists.newArrayList("https://www.googleapis.com/auth/youtube");

        mPlayListArray = new ArrayList<>();
//            Credential credential= Auth.authorize(scopes, "localizations");
//            youTube= new YouTube.Builder(Auth.HTTP_TRANSPORT,Auth.JSON_FACTORY,credential).setApplicationName(context.getString(R.string.app_name)).build();
            youTube= new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest httpRequest) throws IOException {

                }
            }).setApplicationName(context.getString(R.string.app_name)).setYouTubeRequestInitializer(new YouTubeRequestInitializer(context.getString(R.string.youtubeapi))).build();


    }
    public ArrayList<Playlist> getPlaylist(){
        Playlist playlist= null;

        try {
            PlaylistListResponse playlistListResponse= youTube.playlists().list("snippet").setChannelId("UC0iwHRFpv2_fpojZgQhElEQ").execute();
            List<Playlist> playlistList= playlistListResponse.getItems();
            for (Playlist playlist1: playlistList){
                mPlayListArray.add(playlist1);
            }

            String page= playlistListResponse.getNextPageToken();
            playlistListResponse=youTube.playlists().list("snippet").setChannelId("UC0iwHRFpv2_fpojZgQhElEQ").setPageToken(page).execute();
            playlistList= playlistListResponse.getItems();
            for (Playlist playlist1: playlistList) {
                mPlayListArray.add(playlist1);
            }
            page= playlistListResponse.getNextPageToken();
            playlistListResponse=youTube.playlists().list("snippet").setChannelId("UC0iwHRFpv2_fpojZgQhElEQ").setPageToken(page).execute();
            playlistList= playlistListResponse.getItems();
            for (Playlist playlist1: playlistList) {
                mPlayListArray.add(playlist1);
            }
            page= playlistListResponse.getNextPageToken();
            playlistListResponse=youTube.playlists().list("snippet").setChannelId("UC0iwHRFpv2_fpojZgQhElEQ").setPageToken(page).execute();
            playlistList= playlistListResponse.getItems();
            for (Playlist playlist1: playlistList) {
                mPlayListArray.add(playlist1);
            }


        } catch (Throwable thr) {
            thr.printStackTrace();
        }return mPlayListArray;

    }

//    public class GetPlaylistTask extends AsyncTask<Void,Void,Playlist>{
//
//        @Override
//        protected Playlist doInBackground(Void... params) {
//           return getPlaylist();
//        }
//    }
}
