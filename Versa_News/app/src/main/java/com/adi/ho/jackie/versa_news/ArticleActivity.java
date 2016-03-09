package com.adi.ho.jackie.versa_news;

import android.app.FragmentManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

public class ArticleActivity extends AppCompatActivity {
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        mImageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(ArticleActivity.this).load("https://vice-images.vice.com/images/content-images-crops/2016/03/07/the-vice-morning-bulletin-03-07-16-body-image-1457354769-size_1000.jpg").fit().into(mImageView);

    }  //
}
 //