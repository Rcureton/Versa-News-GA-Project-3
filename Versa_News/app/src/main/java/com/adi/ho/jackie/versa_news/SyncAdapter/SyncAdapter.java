package com.adi.ho.jackie.versa_news.SyncAdapter;

import android.accounts.Account;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.adi.ho.jackie.versa_news.GSONClasses.ViceDataClass;
import com.adi.ho.jackie.versa_news.ContentProvider.ViceContentProvider;
import com.adi.ho.jackie.versa_news.ContentProvider.ViceDBHelper;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceItemsClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceSearchResultsClass;
import com.adi.ho.jackie.versa_news.MainActivity;
import com.adi.ho.jackie.versa_news.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Rob on 3/8/16.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    public static final String ARTICLEID = "ID";
    ContentResolver mResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(
            Account account,
            Bundle extras,
            String authority,
            ContentProviderClient provider,
            SyncResult syncResult) {

        getLatestFromAPI();

    }


     public List<ViceItemsClass> getLatestFromAPI(){
       String data = "";
        try {
            URL url = new URL("http://vice.com/api/getlatest/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            data = getInputData(inputStream);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        // Convert the JSON data to Gson data
        Gson gson = new Gson();
        ViceSearchResultsClass results = gson.fromJson(data, ViceSearchResultsClass.class);
        ViceDataClass item = results.getData();

        Log.d("SYNCADAPTOR", "Starting sync");
        for (int i = 0 ; i < 8; i++) {
            Log.d("SYNCADAPTER", results.getData().getItems().get(i).getTitle());
        }

        return item.getItems();


    }


    private String getInputData(InputStream inStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

        String data = null;

        while ((data = reader.readLine()) != null){
            builder.append(data);
        }

        reader.close();

        return builder.toString();
    }
}