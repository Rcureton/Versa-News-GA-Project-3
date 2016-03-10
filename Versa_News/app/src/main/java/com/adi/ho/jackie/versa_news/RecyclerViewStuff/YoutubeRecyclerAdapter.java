package com.adi.ho.jackie.versa_news.RecyclerViewStuff;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adi.ho.jackie.versa_news.R;
import com.adi.ho.jackie.versa_news.Youtube.VideoItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by JHADI on 3/10/16.
 */
public class YoutubeRecyclerAdapter extends RecyclerView.Adapter<YoutubeViewHolder> {
    private ArrayList<VideoItem> mVideos;
    private Context context;

    public YoutubeRecyclerAdapter(Context context, ArrayList<VideoItem> videoItems){
        this.context = context;
        mVideos = videoItems;

    }
    @Override
    public YoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,null);
        YoutubeViewHolder yvh = new YoutubeViewHolder(view);
        return yvh;
    }

    @Override
    public void onBindViewHolder(YoutubeViewHolder holder, int position) {

        holder.videoTitle.setText(mVideos.get(position).getTitle());
        holder.videoDescription.setText(mVideos.get(position).getDescription());
        Picasso.with(context).load(mVideos.get(position).getThumbnailURL()).fit().into(holder.videoThumbnail);
        holder.videoId.setText(mVideos.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }
}
