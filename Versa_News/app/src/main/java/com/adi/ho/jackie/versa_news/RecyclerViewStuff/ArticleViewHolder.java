package com.adi.ho.jackie.versa_news.RecyclerViewStuff;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adi.ho.jackie.versa_news.ArticleActivity;
import com.adi.ho.jackie.versa_news.MainActivity;
import com.adi.ho.jackie.versa_news.R;

import org.w3c.dom.Text;

/**
 * Created by JHADI on 3/8/16.
 */


public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mArticleHeadline;
    public ImageView mArticleImage;
    public TextView mArticlePreview;
    private Context context;
    public TextView mArticleId;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
        itemView.setOnClickListener(this);
        mArticleHeadline = (TextView) itemView.findViewById(R.id.individual_fragment_card_headline);
        mArticleImage = (ImageView)itemView.findViewById(R.id.individual_fragment_card_image);
        mArticlePreview = (TextView)itemView.findViewById(R.id.individual_fragment_card_preview);
        mArticleId = (TextView)itemView.findViewById(R.id.individual_fragment_article_id);

    }

    @Override
    public void onClick(View v) {
        Intent articleIntent = new Intent(v.getContext(), ArticleActivity.class);
        articleIntent.putExtra(MainActivity.ARTICLEID, mArticleId.getText().toString());
        context.startActivity(articleIntent);
    }
}
