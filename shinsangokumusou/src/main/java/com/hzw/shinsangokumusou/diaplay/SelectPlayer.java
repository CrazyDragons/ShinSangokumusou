package com.hzw.shinsangokumusou.diaplay;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.hzw.shinsangokumusou.R;
import com.hzw.shinsangokumusou.database.DataBase;
import com.hzw.shinsangokumusou.maps.Maps;
import com.hzw.shinsangokumusou.music.Music;
import com.hzw.shinsangokumusou.staticvalue.SQLiteValue;

public class SelectPlayer extends AppCompatActivity {

    LinearLayout show_playerLL;
    ProgressBar player_HP, player_atc, player_def, player_power;
    Button player_confirm;
    private SQLiteDatabase sqLiteDatabase;
    private DataBase dataBase;
    private Music music;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dataBase = new DataBase(SelectPlayer.this);
        sqLiteDatabase = dataBase.getWritableDatabase();
        Cursor cursor =  sqLiteDatabase.rawQuery(SQLiteValue.Query_Count_Players, null);
        while (cursor.moveToNext()){
            int count = cursor.getInt(0);
            if (count == 0){
                sqLiteDatabase.execSQL(SQLiteValue.Insert_Player, new Object[]{null, "赵云", 100, 90, 80, 80});
            }
        }
        cursor.close();

        music = new Music(mediaPlayer);
        music.playBGM(SelectPlayer.this, R.raw.select_map_or_player);

        show_playerLL = (LinearLayout) findViewById(R.id.show_player_head);
        for (int i = 0; i < 10; i++) {
            Button imageView = new Button(this);
            imageView.setId(i);
            if ((i % 2) == 0){
                imageView.setBackgroundColor(Color.RED);
            }else {
                imageView.setBackgroundColor(Color.BLUE);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 400);
            layoutParams.setMargins(20, 20, 20, 20);
            /*LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.height = 100;
            layoutParams.width = 100;*/
            imageView.setLayoutParams(layoutParams);
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    set_Plaer_Progress();
                }
            });
            show_playerLL.addView(imageView);
            Log.d("hhzzww", "添加"+i+"图片");
        }

        player_HP = (ProgressBar) findViewById(R.id.player_HP);
        player_atc = (ProgressBar) findViewById(R.id.player_atc);
        player_def = (ProgressBar) findViewById(R.id.player_def);
        player_power = (ProgressBar) findViewById(R.id.player_power);

        player_confirm = (Button) findViewById(R.id.player_confirm);
        player_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectPlayer.this, Maps.class));
                finish();
            }
        });

        /*player_HP.setProgress(Get_play_data("赵云", 1));
        player_atc.setProgress(Get_play_data("赵云", 2));
        player_def.setProgress(Get_play_data("赵云", 3));
        player_power.setProgress(Get_play_data("赵云", 4));*/
    }

    public int Get_play_data(String player_name, int index){

        Cursor cursor = sqLiteDatabase.rawQuery(SQLiteValue.Query_Player_Name, new String[]{player_name});
        int data = 0;
        while (cursor.moveToNext()){
            data = cursor.getInt(index + 1);
        }
        cursor.close();
        return data;
    }

    public void set_Plaer_Progress(){
        player_HP.setProgress((int) (Math.random() * 100));
        player_atc.setProgress((int) (Math.random() * 100));
        player_def.setProgress((int) (Math.random() * 100));
        player_power.setProgress((int) (Math.random() * 100));
    }

    @Override
    protected void onPause() {
        super.onPause();
        music.pauseBGM();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        music.playBGM(SelectPlayer.this, R.raw.select_map_or_player);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        music.stopBGM();
    }

    @Override
    protected void onStop() {
        super.onStop();
        music.stopBGM();
    }
}
