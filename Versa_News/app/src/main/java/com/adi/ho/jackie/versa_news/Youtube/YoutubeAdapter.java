package com.adi.ho.jackie.versa_news.Youtube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adi.ho.jackie.versa_news.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Ra on 3/8/16.
 */
public class YoutubeAdapter extends ArrayAdapter<VideoItem>{
    ArrayList<VideoItem> mVideoItems;
    YoutubeHelper mhelper;

    public YoutubeAdapter(Context context, ArrayList<VideoItem> newVideos) {
        super(context, -1);

        mVideoItems= new ArrayList<VideoItem>();

        mVideoItems = newVideos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = inflater.inflate(R.layout.video_item, parent, false);

        ImageView thumbnail= (ImageView)itemLayout.findViewById(R.id.video_thumbnail);
        TextView videoTitle=(TextView)itemLayout.findViewById(R.id.video_title);
        TextView videoDesc=(TextView)itemLayout.findViewById(R.id.video_description);

        VideoItem currentVideo= mVideoItems.get(position);



        Picasso.with(parent.getContext()).load(currentVideo.getThumbnailURL()).into(thumbnail);
        videoTitle.setText(currentVideo.getTitle());
        videoDesc.setText(currentVideo.getDescription());

        return itemLayout;
    }

    @Override
    public int getCount() {
        return mVideoItems.size();
    }
}
