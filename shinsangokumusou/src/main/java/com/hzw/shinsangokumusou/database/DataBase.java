package com.hzw.shinsangokumusou.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.database/DataBase
 * Created by HZW
 * Data 2017/11/19
 * Time 1:35
 */

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context) {
        super(context, "shinsangokumusou.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE player_data(id integer primary key autoincrement," +
                "player_name text, player_HP integer, player_atc integer, player_def integer, player_power integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
