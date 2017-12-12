package com.hzw.shinsangokumusou.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hzw.shinsangokumusou.staticvalue.SQLiteValue;

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
        //创建玩家角色表
        sqLiteDatabase.execSQL(SQLiteValue.Create_Table_Player);
        sqLiteDatabase.execSQL(SQLiteValue.Create_Table_Chess);
        sqLiteDatabase.execSQL(SQLiteValue.Create_Table_TEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
