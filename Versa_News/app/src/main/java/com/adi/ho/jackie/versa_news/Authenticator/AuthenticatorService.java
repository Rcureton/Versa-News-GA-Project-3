package com.adi.ho.jackie.versa_news.Authenticator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Rob on 3/9/16.
 */
public class AuthenticatorService extends Service {
    // Instance field that stores the authenticator object
    private AuthenticatorService mAuthenticator;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new AuthenticatorService();
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}