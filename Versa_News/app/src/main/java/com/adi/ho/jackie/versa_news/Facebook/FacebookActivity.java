package com.adi.ho.jackie.versa_news.Facebook;

import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.adi.ho.jackie.versa_news.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONObject;

import java.util.Arrays;

public class FacebookActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook);
        //TODO: I believe this needs to stay on the Activity on the OnCreate

        AccessToken accessToken= new AccessToken(getString(R.string.accessToken),
            getString(R.string.facebook_app_id),getString(R.string.facebook_app_secret),null,null,null,null,null);

        GraphRequest request = GraphRequest.newGraphPathRequest(accessToken,"1534610320190530",
                new GraphRequest.Callback(){
                    @Override
                    public void onCompleted(GraphResponse response) {
                        Log.v("Output",response.getJSONObject().toString());
                    }
            });
        GraphRequest.executeBatchAsync(request);



        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }

         });
        LoginManager.getInstance().logInWithPublishPermissions(
                FacebookActivity.this,
                Arrays.asList("publish_actions"));
    }


//                //TODO: NEED THESE TO CREATE THE SHARE DIALOG
//                callbackManager = CallbackManager.Factory.create();
//        shareDialog = new ShareDialog(this);

//        //TODO: CREATES A DIALOG OF WHAT YOU'RE SHARING, PROMPTS LOG IN
//
//        if (ShareDialog.canShow(ShareLinkContent.class)) {
//            ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                    .setContentTitle("Hello Facebook")
//                    .setContentDescription(
//                            "The 'Hello Facebook' sample  showcases simple Facebook integration")
//                    .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
//                    .build();
//
//            shareDialog.show(linkContent);
//        }
//
//        //TODO: THIS IS FOR A LIKE BUTTON PROMPTS THE SAME LOG IN
//        LikeView likeView = (LikeView) findViewById(R.id.likeView);
//        likeView.setObjectIdAndType(
//                "https://www.facebook.com/FacebookDevelopers",
//                LikeView.ObjectType.PAGE);
//        //TODO: THIS IS FOR THE SHARE BUTTON THAT FB GENERATED FOR ANDROID
//        ShareButton shareButton = (ShareButton)findViewById(R.id.sharebutton);
//        shareButton.setShareContent(content);
//}
//    //TODO: NOT SURE IF WE NEED THIS BECAUSE THERE WILL A SHARE BUTTON WITHIN THE ARTICLE WHICH IS THE CONTENT SO NO LINK NEEDED
//    ShareLinkContent content = new ShareLinkContent.Builder()
//            .setContentUrl(Uri.parse("https://developers.facebook.com"))
//            .build();
//
//    //TODO: NEED THIS METHOD FOR HANDLING THE RESULT OF THE CALLBACK
//    @Override
//    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


