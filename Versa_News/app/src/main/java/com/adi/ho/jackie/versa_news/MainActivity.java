package com.adi.ho.jackie.versa_news;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.adi.ho.jackie.versa_news.Facebook.FacebookActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;

import com.adi.ho.jackie.versa_news.Youtube.PlayVideos;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//
        Button facebook= (Button)findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, FacebookActivity.class);
                startActivity(intent);
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayVideos.class);
                startActivity(intent);
            }
        });
    }
}

