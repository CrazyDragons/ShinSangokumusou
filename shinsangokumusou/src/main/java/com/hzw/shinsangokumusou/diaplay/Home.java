package com.hzw.shinsangokumusou.diaplay;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.hzw.shinsangokumusou.R;
import com.hzw.shinsangokumusou.music.BGM;
import com.hzw.shinsangokumusou.music.SoundEffects;
import com.hzw.shinsangokumusou.utils.ActivityUtils;
import com.hzw.shinsangokumusou.utils.ToastUtil;
import com.hzw.shinsangokumusou.video.PlayMV;

import java.util.TimerTask;

/**
 * 欢迎页
 */

public class Home extends BaseDisplay implements View.OnClickListener{

    Button btn_select_map, btn_show_player, btn_show_MV, btn_game_setting, btn_game_exit;
    MediaPlayer mediaPlayer;
    BGM BGM = new BGM(mediaPlayer);
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

    }

    public void init(){

        btn_select_map = (Button) findViewById(R.id.select_map);
        btn_show_player = (Button) findViewById(R.id.show_player);
        btn_show_MV = (Button) findViewById(R.id.show_MV);
        btn_game_setting = (Button) findViewById(R.id.game_setting);
        btn_game_exit = (Button) findViewById(R.id.game_exit);

        btn_select_map.setOnClickListener(this);
        btn_show_player.setOnClickListener(this);
        btn_show_MV.setOnClickListener(this);
        btn_game_setting.setOnClickListener(this);
        btn_game_exit.setOnClickListener(this);

        BGM.playBGM(Home.this, R.raw.music_select_mode);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.select_map:
                new SoundEffects(Home.this, new SoundPool.Builder().build()).playSoundEffects(R.raw.soundeffects_commit);
                new ActivityUtils().DelayJunm(Home.this, SelectMap.class, 1000);
                break;
            case R.id.show_player:
                break;
            case R.id.show_MV:
                new SoundEffects(Home.this, new SoundPool.Builder().build()).playSoundEffects(R.raw.soundeffects_commit);
                new ActivityUtils().DelayJunm(Home.this, PlayMV.class, 1000);
                break;
            case R.id.game_setting:
                break;
            case R.id.game_exit:
                new AlertDialog.Builder(this).setTitle("提示")
                        .setMessage("是否要退出？")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                break;
            default:break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis() - exitTime) > 2000){
                ToastUtil.args_0(getApplicationContext(), "再按一次退出");
                exitTime = System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        BGM.stopBGM();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BGM.pauseBGM();
    }

    @Override
    protected void onStop() {
        super.onStop();
        BGM.pauseBGM();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new SoundEffects(Home.this, new SoundPool.Builder().build()).playSoundEffects(R.raw.soundeffects_cancel);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                BGM.playBGM(Home.this, R.raw.music_select_mode);
            }
        };
        ActivityUtils.DelaTask(timerTask, 1000);
    }


}