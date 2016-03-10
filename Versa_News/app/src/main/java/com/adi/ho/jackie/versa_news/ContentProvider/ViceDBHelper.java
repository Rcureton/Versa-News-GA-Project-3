package com.adi.ho.jackie.versa_news.ContentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rob on 3/8/16.
 */
public class ViceDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "viceDB.db";
    public static final String TABLE_VICE = "VICE";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TAGS = "tags";
    public static final String COLUMN_PREVIEW = "preview";
    public static final String COLUMN_BODY = "body";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_PUBDATE = "pub_date";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_THUMB = "thumb";
    public static final String COLUMN_IMAGE = "image";

    public static final String[] ALL_COLUMNS = new String[]{COLUMN_ID, COLUMN_AUTHOR, COLUMN_BODY,
            COLUMN_CATEGORY, COLUMN_ID, COLUMN_IMAGE, COLUMN_PREVIEW, COLUMN_PUBDATE, COLUMN_TAGS,
            COLUMN_THUMB, COLUMN_TITLE};

    public ViceDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_VICE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_AUTHOR + " TEXT,"
                + COLUMN_BODY + " TEXT,"
                + COLUMN_CATEGORY + "TEXT,"
                + COLUMN_ID + " TEXT,"
                + COLUMN_IMAGE + " TEXT,"
                + COLUMN_PREVIEW + " TEXT,"
                + COLUMN_PUBDATE + " TEXT,"
                + COLUMN_TAGS + " TEXT,"
                + COLUMN_THUMB + " TEXT,"
                + COLUMN_TITLE + " TEXT)";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VICE);
        onCreate(db);
    }

    public int updateArticleById(String id, ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();

        int numRowsChanged = db.update(TABLE_VICE, values, COLUMN_ID + " = ?", new String[]{id});
        db.close();

        return numRowsChanged;
    }

    public Cursor getData(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_VICE, ALL_COLUMNS, null, null, null, null, null);

        return cursor;
    }

    public Cursor getArticleById(String id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_VICE, ALL_COLUMNS, COLUMN_ID+" = ?", new String[]{id}, null, null, null);

        return cursor;
    }


    // TODO: When data is synced, delete table and insert new data.
    // TODO: Add INSERT method, and call when data is received and loop through new articles received.
}
