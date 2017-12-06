package com.hzw.shinsangokumusou.video;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.hzw.shinsangokumusou.R;

public class PlayMV extends AppCompatActivity implements View.OnClickListener{

    ImageView MV_1, MV_2, MV_3, MV_4, MV_5, MV_6;
    VideoView videoView;
    MediaController mediaController;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mv);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        videoView = (VideoView) findViewById(R.id.play_MV);
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();

        MV_1 = (ImageView) findViewById(R.id.MV_1);
        MV_2 = (ImageView) findViewById(R.id.MV_2);
        MV_3 = (ImageView) findViewById(R.id.MV_3);
        MV_4 = (ImageView) findViewById(R.id.MV_4);
        MV_5 = (ImageView) findViewById(R.id.MV_5);
        MV_6 = (ImageView) findViewById(R.id.MV_6);
        MV_1.setOnClickListener(this);
        MV_2.setOnClickListener(this);
        MV_3.setOnClickListener(this);
        MV_4.setOnClickListener(this);
        MV_5.setOnClickListener(this);
        MV_6.setOnClickListener(this);

        /*videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:
                        if ((System.currentTimeMillis() - exitTime) < 500){
                            startActivity(new Intent(PlayMV.this, FullScreenMV.class));
                            exitTime = System.currentTimeMillis();
                        }else {
                            exitTime = System.currentTimeMillis();
                        }
                        break;
                }
                return true;
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.MV_1:
                videoView.setVideoURI(Uri.parse ("android.resource://"+getPackageName ()+"/"+R.raw.cg));
                videoView.start();
                break;
            case R.id.MV_2:
                videoView.setVideoURI(Uri.parse ("android.resource://"+getPackageName ()+"/"+R.raw.ending_cg));
                videoView.start();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.suspend();
    }
}
