package com.adi.ho.jackie.versa_news.RecyclerViewStuff;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adi.ho.jackie.versa_news.R;
import com.adi.ho.jackie.versa_news.Youtube.PlayVideos;
import com.adi.ho.jackie.versa_news.Youtube.VideoItem;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.ArrayList;

/**
 * Created by JHADI on 3/10/16.
 */
public class YoutubeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private Context context;
    public ImageView videoThumbnail;
    public TextView videoTitle;
    public TextView videoDescription;
    public TextView videoId;

    public YoutubeViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        videoDescription = (TextView)itemView.findViewById(R.id.video_description);
        videoThumbnail = (ImageView)itemView.findViewById(R.id.video_thumbnail);
        videoTitle = (TextView)itemView.findViewById(R.id.video_title);
        videoId = (TextView)itemView.findViewById(R.id.video_id);
    }
    @Override
    public void onClick(View v) {
        Intent intent = YouTubeStandalonePlayer.createPlaylistIntent((Activity)context,
                PlayVideos.KEY, videoId.getText().toString());
        context.startActivity(intent);
    }


}
