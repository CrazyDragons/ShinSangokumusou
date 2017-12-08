package com.hzw.shinsangokumusou.interfaces;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.interfaces/DBUtils
 * Created by HZW
 * Data 2017/12/8
 * Time 18:21
 */

public interface DBUtils {
    public SQLiteDatabase getDB();
    public Cursor getCursor(String sql, String[] strings);
    public void InsertDB(String sql, String[] strings);
    public void closeCursor(Cursor cursor);
    public void closeDB();
    // TODO: 2017/12/8 18:27 写完数据库接口
}
