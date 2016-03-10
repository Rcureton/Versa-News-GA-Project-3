
package com.adi.ho.jackie.versa_news;


import android.app.FragmentManager;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.adi.ho.jackie.versa_news.GSONClasses.ViceArticleClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceDataClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceItemsClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceSearchResultsClass;
import com.adi.ho.jackie.versa_news.RecyclerViewStuff.ArticleRecyclerAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class ArticleActivity extends AppCompatActivity {

    private static final String TWITTER_KEY = "h8Rnv9R1AdZlL4s1joefXvY3i";
    private static final String TWITTER_SECRET = "NB0PEP1A7uPk8cxgWZzClz8AQthBSi9UbzE3sfDO0voBWqIiPT";
    File myImageFile = new File("/path/to/image");
    Uri myImageUri = Uri.fromFile(myImageFile);

    private ImageView articleImage;
    private TextView articleAuthor;
    private TextView articleDate;
    private WebView articleBody;
    private TextView articleCategory;
    private TextView articleTitle;
    private String articleId;
    private ImageView mImageView;
    private ImageButton mTwitterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig), new TwitterCore(authConfig), new TweetComposer());
        setContentView(R.layout.activity_article);


        mTwitterButton = (ImageButton)findViewById(R.id.twitterButton);
        mTwitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TweetComposer.Builder builder = new TweetComposer.Builder(ArticleActivity.this)
                        .text("Check out what I found on Versa!")
                        .image(myImageUri);
                builder.show();
            }
        });

//        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
//        loginButton.setCallback(new Callback<TwitterSession>() {
//
//            @Override
//            public void success(Result<TwitterSession> result) {
//
//                // The TwitterSession is also available through:
//                TwitterSession session = Twitter.getInstance().core.getSessionManager().getActiveSession();
//                // TODO: Remove toast and use the TwitterSession's userID
//                // with your app's user model
//                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
//
//            }

//            public void failure(TwitterException exception) {
//                Log.d("TwitterKit", "Login with Twitter failure", exception);
//            }
//        });

        mImageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(ArticleActivity.this).load("https://vice-images.vice.com/images/content-images-crops/2016/03/07/the-vice-morning-bulletin-03-07-16-body-image-1457354769-size_1000.jpg").fit().into(mImageView);

        //

        //

        articleImage = (ImageView) findViewById(R.id.imageView);
        articleAuthor = (TextView) findViewById(R.id.authorTextView);
        articleDate = (TextView) findViewById(R.id.dateTextView);
        articleBody = (WebView) findViewById(R.id.articleText);
        articleCategory = (TextView) findViewById(R.id.articleCategory);
        articleTitle = (TextView) findViewById(R.id.titleView);

        //  articleBody.getSettings().setLoadWithOverviewMode(true);
        articleBody.getSettings().setUseWideViewPort(false);

        Intent idIntent = getIntent();

        if (idIntent != null) {
            articleId = idIntent.getStringExtra(MainActivity.ARTICLEID);
            new LoadArticle().execute(articleId);
        }


    }


    private class LoadArticle extends AsyncTask<String, Void, ViceArticleClass> {


        @Override
        protected ViceArticleClass doInBackground(String... params) {
            String data = "";
            try {
                URL url = new URL("http://vice.com/api/article/" + params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                data = getInputData(inputStream);
            } catch (Throwable e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            ViceSearchResultsClass results = gson.fromJson(data, ViceSearchResultsClass.class);

            ViceDataClass item = results.getData();

            return item.getArticle();

        }

        @Override
        protected void onPostExecute(ViceArticleClass viceArticle) {
            String title = viceArticle.getTitle();
            String date = viceArticle.getPubDate();
            String author = viceArticle.getAuthor();
            String body = viceArticle.getBody();
            String category = viceArticle.getCategory();
            String urlImage = viceArticle.getImage();
            final String article = "<style>img{display: inline; height: auto; max-width: 100%;}</style>"+body;
            articleTitle.setText(title);
            articleDate.setText(date);
            articleAuthor.setText(author);
            articleBody.post(new Runnable() {
                @Override
                public void run() {
                    articleBody.loadData(article,"text/html",null);
                }
            });
            articleCategory.setText(category);
            Picasso.with(ArticleActivity.this).load(urlImage).fit().into(articleImage);

        }

        public String getInputData(InputStream stream) throws IOException {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String read;

            while ((read = br.readLine()) != null) {
                sb.append(read);
            }

            br.close();
            return sb.toString();
        }

    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // Make sure that the loginButton hears the result from any
//        // Activity that it triggered.
//        loginButton.onActivityResult(requestCode, resultCode, data);
//    }

}

