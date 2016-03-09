package com.adi.ho.jackie.versa_news.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Rob on 3/8/16.
 */
public class ViceContentProvider extends ContentProvider {

    //TODO: Update everything stock-related to be Vice-related.
    private static final String AUTHORITY = "com.adi.ho.jackie.versa_news.ContentProvider.ViceContentProvider";
    private static final String STOCK_PRICES_TABLE = ViceDBHelper.TABLE_VICE;
    public static final Uri CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + STOCK_PRICES_TABLE);

    public static final int STOCK = 1;
    public static final int STOCK_ID = 2;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, STOCK_PRICES_TABLE, STOCK);
        sURIMatcher.addURI(AUTHORITY, STOCK_PRICES_TABLE + "/#", STOCK_ID);
    }

    private ViceDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new ViceDBHelper(getContext(), null, null, 1);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int uriType = sURIMatcher.match(uri);

        Cursor cursor;

        switch (uriType) {
            case STOCK_ID:
                cursor = dbHelper.getArticleById(uri.getLastPathSegment());
                break;
            case STOCK:
                cursor = dbHelper.getData();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        int rowsUpdated = 0;

        Log.d(ViceContentProvider.class.getName(), "Triggered update");

        switch (uriType) {
            case STOCK_ID:
                rowsUpdated = dbHelper.updateArticleById(uri.getLastPathSegment(), values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}