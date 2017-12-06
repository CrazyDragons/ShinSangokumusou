package com.hzw.shinsangokumusou.staticvalue;

/**
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.staticvalue/SQLiteValue
 * Created by HZW
 * Data 2017/12/6
 * Time 19:57
 */

public class SQLiteValue {
    public static String Query_Count_Players = "select count(*) from player_data";
    public static String Insert_Player = "insert into player_data values(?, ?, ?, ?, ?, ?)";
    public static String Query_Player_Name = "select * from player_data where player_name = ?";
}
