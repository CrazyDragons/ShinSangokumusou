package com.hzw.shinsangokumusou.staticvalue;

/**
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.staticvalue/SQLiteValue
 * Created by HZW
 * Data 2017/12/6
 * Time 19:57
 */

public class SQLiteValue {
    /**
     * 创建表
     */
    //创建角色表
    public static String Create_Table_Player = "CREATE TABLE player_data(id integer primary key autoincrement," +
            "player_name text, player_HP integer, player_atc integer, player_def integer, player_power integer)";

    /**
     * 插入
     */
        /*
        角色
         */
        //插入角色
        public static String Insert_Player = "insert into player_data values(?, ?, ?, ?, ?, ?)";

    /**
     * 查询
     */
        /*
        角色
         */
        //查询角色数
        public static String Query_Count_Players = "select count(*) from player_data";
        //查询角色名
        public static String Query_Player_Name = "select * from player_data where player_name = ?";
}
