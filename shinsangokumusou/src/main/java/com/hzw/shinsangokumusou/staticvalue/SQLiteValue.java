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
    //创建棋表
    public static String Create_Table_Chess = "CREATE TABLE chess_data(id integer primary key autoincrement," +
            "ChessName text, Moving integer, Complete integer)";
    public static String Create_Table_TEST = "CREATE TABLE test_data(id integer primary key autoincrement," +
            "ChessName integer， Moving integer)";

    /**
     * 插入
     */
        /*
        角色
         */
            //插入角色
            public static String Insert_Player = "insert into player_data values(?, ?, ?, ?, ?, ?)";
        /*
        角色
        */
            //插入角色
            public static String Insert_Chess = "insert into chess_data values(?, ?, ?, ?)";

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
        /*
        棋
         */
            //查询棋子数
        public static String Query_Count_Chess = "select count(*) from chess_data";

    public static String Query_Chess_Name = "select * from test_data where id = 1";
    public static String Update_Chess_Name = "update test_data set ChessName = ? where id = 1" ;

}
