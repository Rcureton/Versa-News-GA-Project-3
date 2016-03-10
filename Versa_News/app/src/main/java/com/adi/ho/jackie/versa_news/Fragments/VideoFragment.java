//package com.adi.ho.jackie.versa_news.Fragments;
//
//
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.adi.ho.jackie.versa_news.R;
//import com.adi.ho.jackie.versa_news.RecyclerViewStuff.YoutubeRecyclerAdapter;
//import com.adi.ho.jackie.versa_news.Youtube.VideoItem;
//import com.adi.ho.jackie.versa_news.Youtube.YoutubeAdapter;
//import com.adi.ho.jackie.versa_news.Youtube.YoutubeHelper;
//import com.google.android.youtube.player.YouTubeInitializationResult;
//import com.google.android.youtube.player.YouTubePlayer;
//import com.google.android.youtube.player.YouTubePlayerFragment;
//import com.google.android.youtube.player.YouTubePlayerSupportFragment;
//import com.google.android.youtube.player.YouTubePlayerView;
//import com.google.android.youtube.player.YouTubeStandalonePlayer;
//import com.google.api.services.youtube.YouTube;
//import com.google.api.services.youtube.model.Playlist;
//
//import java.util.ArrayList;
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class VideoFragment extends YouTubePlayerSupportFragment {
//    private YouTube youtube;
//    private YouTube.Search.List query;
//    private YouTubePlayerView playerView;
//    private ArrayList<Playlist> mPlayListArrayList;
//    public YoutubeHelper helper;
//    private RecyclerView videoRecycler;
//
//    ListView mVideosList;
//    ArrayList<VideoItem> mVideos;
//
//    public static final String KEY
//            = "AIzaSyCp4VCk-TmZPT82SmD_XISh4fx1oz8S_JE";
//
//    public VideoFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.activity_play_videos,container,false);
//        videoRecycler = (RecyclerView)view.findViewById(R.id.player_view);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        videoRecycler.setHasFixedSize(true);
//        videoRecycler.setLayoutManager(linearLayoutManager);
//
//
//        mVideos= new ArrayList<>();
//
//        LoadYoutubeTask load= new LoadYoutubeTask();
//        load.execute();
//
//        return view;
//
//    }
//
////    @Override
////    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restored) {
////        if(!restored){
////            youTubePlayer.cueVideo(getActivity().getIntent().getStringExtra("video"));
////        }
////
////    }
//
//
//
//
//    public ArrayList<Playlist> getVideosList(){
//        helper= new YoutubeHelper(getActivity());
//        mPlayListArrayList = new ArrayList<>();
//
//        mPlayListArrayList = helper.getPlaylist();
//        for (Playlist playlist : mPlayListArrayList) {
//            VideoItem videoItem = new VideoItem();
//            try{
//                videoItem.setThumbnailURL(playlist.getSnippet().getThumbnails().getStandard().getUrl());
//            }catch (Throwable throwable){
//                videoItem.setThumbnailURL("http://images.fineartamerica.com/images-medium-large/2-nyc-empire-nina-papiorek.jpg");
//            }
//            videoItem.setId(playlist.getId());
//            Log.d("Video",videoItem.getId());
//
//            videoItem.setTitle(playlist.getSnippet().getTitle());
//            videoItem.setDescription(playlist.getSnippet().getDescription());
//
//            mVideos.add(videoItem);
//        }return mPlayListArrayList;
//
//    }
//
//
//    public class LoadYoutubeTask extends AsyncTask<Void, Void, ArrayList<Playlist>> {
//
//
//        @Override
//        protected ArrayList<Playlist> doInBackground(Void... params) {
//            return getVideosList();
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Playlist> playlists) {
//            super.onPostExecute(playlists);
//            YoutubeRecyclerAdapter youtubeAdapter = new YoutubeRecyclerAdapter(getContext(),mVideos);
//            videoRecycler.setAdapter(youtubeAdapter);
//
//        }
//    }
//
//}
