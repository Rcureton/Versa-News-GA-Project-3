//package com.adi.ho.jackie.versa_news;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.content.Intent;
//import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import io.fabric.sdk.android.Fabric;
//import com.twitter.sdk.android.Twitter;
//import com.twitter.sdk.android.core.Callback;
//import com.twitter.sdk.android.core.Result;
//import com.twitter.sdk.android.core.TwitterAuthConfig;
//import com.twitter.sdk.android.core.TwitterCore;
//import com.twitter.sdk.android.tweetcomposer.TweetComposer;
//import com.twitter.sdk.android.core.TwitterException;
//import com.twitter.sdk.android.core.TwitterSession;
//import com.twitter.sdk.android.core.identity.TwitterLoginButton;
//import java.io.File;
//
//
//public class TwitterActivity extends AppCompatActivity {
//    private TwitterLoginButton loginButton;
//    private static final String TWITTER_KEY = "h8Rnv9R1AdZlL4s1joefXvY3i";
//    private static final String TWITTER_SECRET = "NB0PEP1A7uPk8cxgWZzClz8AQthBSi9UbzE3sfDO0voBWqIiPT";
//    File myImageFile = new File("/path/to/image");
//    Uri myImageUri = Uri.fromFile(myImageFile);
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO (Pass variable into the .text, and put all code into own class and onClick.)
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig), new TwitterCore(authConfig), new TweetComposer());
//        setContentView(R.layout.activity_main);
//
//        TweetComposer.Builder builder = new TweetComposer.Builder(this)
//                .text("just setting up my Fabric.")
//                .image(myImageUri);
//        builder.show();
//
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
//
//            public void failure(TwitterException exception) {
//                Log.d("TwitterKit", "Login with Twitter failure", exception);
//            }
//        });
//    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // Make sure that the loginButton hears the result from any
//        // Activity that it triggered.
//        loginButton.onActivityResult(requestCode, resultCode, data);
//    }
//
//}
