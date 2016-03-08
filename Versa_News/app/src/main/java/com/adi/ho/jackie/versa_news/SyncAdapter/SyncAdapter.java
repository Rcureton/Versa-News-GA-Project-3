package com.adi.ho.jackie.versa_news.SyncAdapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.adi.ho.jackie.versa_news.GSONClasses.ViceDataClass;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rob on 3/8/16.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
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
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Log.d(SyncAdapter.class.getName(), "Starting sync");

        Cursor cursor = mResolver.query(ViceContentProvider.CONTENT_URI,null,null,null,null);

        ViceDataClass stock;
        while(cursor.moveToNext()){
            stock = getData(cursor.getString(cursor.getColumnIndex(ViceDBHelper.COLUMN_STOCK_SYMBOL)));
            Uri uri = Uri.parse(ViceContentProvider.CONTENT_URI + "/" + cursor.getString(cursor.getColumnIndex(ViceDBHelper.COLUMN_ID)));
            ContentValues values = new ContentValues();
            values.put(ViceDBHelper.COLUMN_STOCK_PRICE, getData().getItems());
            mResolver.update(uri,values,null,null);
        }

    }

    private ViceDataClass getData(String viceData){
        String data ="";
        try {
            URL url = new URL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inStream = connection.getInputStream();
            data = getInputData(inStream);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return gson.fromJson(data,ViceDataClass.class);
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