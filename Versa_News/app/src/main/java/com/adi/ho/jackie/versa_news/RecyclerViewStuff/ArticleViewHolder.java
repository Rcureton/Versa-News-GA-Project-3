package com.adi.ho.jackie.versa_news.RecyclerViewStuff;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adi.ho.jackie.versa_news.R;

import org.w3c.dom.Text;

/**
 * Created by JHADI on 3/8/16.
 */


public class ArticleViewHolder extends RecyclerView.ViewHolder {

    public TextView mArticleHeadline;
    public ImageView mArticleImage;
    public TextView mArticlePreview;
    private Context context;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
        mArticleHeadline = (TextView) itemView.findViewById(R.id.individual_fragment_card_headline);
        mArticleImage = (ImageView)itemView.findViewById(R.id.individual_fragment_card_image);
        mArticlePreview = (TextView)itemView.findViewById(R.id.individual_fragment_card_preview);

    }
}
