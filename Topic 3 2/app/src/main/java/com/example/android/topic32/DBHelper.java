package com.example.android.topic32;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "songsDb";
    public static final String TABLE_SONGS = "songs";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_BAND = "band";
    public static final String KEY_STYLE = "style";
    public static final String KEY_URI = "uri";

    private Context context;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_SONGS + "(" + KEY_ID
                + " integer primary key," + KEY_NAME + " text,"  + KEY_BAND + " text," + KEY_STYLE + " text," + KEY_URI + " text" + ")");

        addSongsToDb(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_SONGS);
        onCreate(db);
    }

    private void addSongsToDb(SQLiteDatabase db) {
        SongsForDB songsForDB = new SongsForDB(context.getPackageName());
        List<Song> songs = songsForDB.getListOfSongs();
        for (Song song : songs) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.KEY_NAME, song.getSongName());
            contentValues.put(DBHelper.KEY_BAND, song.getSongBand());
            contentValues.put(DBHelper.KEY_STYLE, song.getSongStyle());
            contentValues.put(DBHelper.KEY_URI, song.getSongId());
            db.insert(DBHelper.TABLE_SONGS, null, contentValues);
        }
    }
}


