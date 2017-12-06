package com.hzw.shinsangokumusou.video;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.hzw.shinsangokumusou.R;

public class FullScreenMV extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_mv);

        videoView = (VideoView) findViewById(R.id.full_MV);

    }
}
