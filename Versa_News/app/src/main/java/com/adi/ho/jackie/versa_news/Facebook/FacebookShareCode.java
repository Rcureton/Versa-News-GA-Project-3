package com.adi.ho.jackie.versa_news.Facebook;

import android.content.Intent;
import android.net.Uri;

import com.adi.ho.jackie.versa_news.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by Ra on 3/8/16.
 */
public class FacebookShareCode {
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    //TODO: ALL THE CODE CAN BE DELETED ONCE DONE

    //TODO: I believe this needs to stay on the Activity on the OnCreate
    FacebookSdk.sdkInitialize(getApplicationContext());

    //TODO: NEED THESE TO CREATE THE SHARE DIALOG
    callbackManager = CallbackManager.Factory.create();
    shareDialog = new ShareDialog(this);


//TODO: CREATES A DIALOG OF WHAT YOU'RE SHARING, PROMPTS LOG IN

    if (ShareDialog.canShow(ShareLinkContent.class)) {
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentTitle("Hello Facebook")
                .setContentDescription(
                        "The 'Hello Facebook' sample  showcases simple Facebook integration")
                .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                .build();

        shareDialog.show(linkContent);
    }

    //TODO: THIS IS FOR A LIKE BUTTON PROMPTS THE SAME LOG IN
    LikeView likeView = (LikeView) findViewById(R.id.likeView);
    likeView.setObjectIdAndType(
            "https://www.facebook.com/FacebookDevelopers",
    LikeView.ObjectType.PAGE);
    //TODO: THIS IS FOR THE SHARE BUTTON THAT FB GENERATED FOR ANDROID
    ShareButton shareButton = (ShareButton)findViewById(R.id.sharebutton);
    shareButton.setShareContent(content);
}
    //TODO: NOT SURE IF WE NEED THIS BECAUSE THERE WILL A SHARE BUTTON WITHIN THE ARTICLE WHICH IS THE CONTENT SO NO LINK NEEDED
ShareLinkContent content = new ShareLinkContent.Builder()
        .setContentUrl(Uri.parse("https://developers.facebook.com"))
        .build();

    //TODO: NEED THIS METHOD FOR HANDLING THE RESULT OF THE CALLBACK
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

}
