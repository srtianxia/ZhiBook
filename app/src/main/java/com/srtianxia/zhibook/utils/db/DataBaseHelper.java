package com.srtianxia.zhibook.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by srtianxia on 2016/2/21.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    private Context context;

    private static final String CREATE_TABLE_NOTE = "create table note (" +
            "id integer primary key autoincrement," +
            "content text," +
            "authorId integer)";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
