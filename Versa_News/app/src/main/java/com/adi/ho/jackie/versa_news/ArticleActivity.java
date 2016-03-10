package com.adi.ho.jackie.versa_news;

<<<<<<< HEAD
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
=======
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ArticleActivity extends AppCompatActivity {

    private ImageView articleImage;
    private TextView articleAuthor;
    private TextView articleDate;
    private WebView articleBody;
    private TextView articleCategory;
    private TextView articleTitle;
    private String articleId;
>>>>>>> master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

<<<<<<< HEAD
        mImageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(ArticleActivity.this).load("https://vice-images.vice.com/images/content-images-crops/2016/03/07/the-vice-morning-bulletin-03-07-16-body-image-1457354769-size_1000.jpg").fit().into(mImageView);

    }  //
}
 //
=======
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
}
>>>>>>> master
