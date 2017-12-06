package com.hzw.shinsangokumusou.video;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.VideoView;

import com.hzw.shinsangokumusou.R;
import com.hzw.shinsangokumusou.diaplay.Home;

public class CG extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cg);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        videoView = (VideoView) findViewById(R.id.play_CG);
        videoView.setVideoURI(Uri.parse ("android.resource://"+getPackageName ()+"/"+R.raw.cg));
        videoView.start();
        videoView.requestFocus();
        videoView.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View view, MotionEvent motionEvent) {
               switch (motionEvent.getAction()){
                   case MotionEvent.ACTION_UP:
                       startActivity(new Intent(CG.this, Home.class));
                       finish();
               }
               return true;
           }
       });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                startActivity(new Intent(CG.this, Home.class));
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.suspend();
    }
}
