package com.adi.ho.jackie.versa_news.RecyclerViewStuff;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adi.ho.jackie.versa_news.GSONClasses.ViceItemsClass;
import com.adi.ho.jackie.versa_news.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by JHADI on 3/8/16.
 */
public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<ViceItemsClass> viceArticleList;
    private Context context;

    public ArticleRecyclerAdapter(Context context, List<ViceItemsClass> articleList){
        this.context = context;
        viceArticleList = articleList;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_fragment_layout, null);
        ArticleViewHolder articleViewHolder = new ArticleViewHolder(view);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        String urlImage = viceArticleList.get(position).getImage(); // TODO: get the real url here
        String headline = viceArticleList.get(position).getTitle();
        String preview = viceArticleList.get(position).getPreview();
        String articleId = viceArticleList.get(position).getId();

        Picasso.with(context).load(urlImage).fit().into(holder.mArticleImage);
        holder.mArticleHeadline.setText(headline);
        holder.mArticlePreview.setText(preview);
        holder.mArticleId.setText(articleId);

    }

    @Override
    public int getItemCount() {
        return viceArticleList.size();
//        return 0;
    }
}
