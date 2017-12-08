package com.hzw.shinsangokumusou.diaplay;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hzw.shinsangokumusou.R;
import com.hzw.shinsangokumusou.music.Music;
import com.hzw.shinsangokumusou.staticvalue.MapsValue;

import static com.hzw.shinsangokumusou.R.drawable.maps_init;

public class SelectMap extends AppCompatActivity {

    private Music music;
    private MediaPlayer mediaPlayer;
    private ListView listView;
    private ImageView showmap;
    private TextView text_map_name, text_map_yaer, VS_A, VS_B;
    private RadioButton select_A, select_B;
    private RatingBar ratingBar;
    private Button map_ok;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_map);

        init();

    }

    public void init(){

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        music = new Music(mediaPlayer);
        music.playBGM(SelectMap.this, R.raw.music_select_map_or_player);

        showmap = (ImageView) findViewById(R.id.image_show_map);
        text_map_name = (TextView) findViewById(R.id.text_map_name);
        text_map_yaer = (TextView) findViewById(R.id.text_map_yaer);
        VS_A = (TextView) findViewById(R.id.VS_A);
        VS_B = (TextView) findViewById(R.id.VS_B);
        select_A = (RadioButton) findViewById(R.id.select_A);
        select_B = (RadioButton) findViewById(R.id.select_B);
        ratingBar = (RatingBar) findViewById(R.id.map_rating);
        map_ok = (Button) findViewById(R.id.map_ok);

        showmap.setImageResource(maps_init);

        map_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectMap.this, SelectPlayer.class));
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.listview_map);
        listView.setAdapter(new ArrayAdapter<String>(SelectMap.this, android.R.layout.simple_list_item_1, MapsValue.Maps_Name));
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showmap.setImageResource(MapsValue.Maps_Images[i]);
                text_map_name.setText(MapsValue.Maps_Name[i]);
                text_map_yaer.setText(MapsValue.Maps_Yaer[i]);
                VS_A.setText(MapsValue.Maps_Select_A[i]);
                VS_B.setText(MapsValue.Maps_Select_B[i]);
                select_A.setText(MapsValue.Maps_Select_A[i]);
                select_B.setText(MapsValue.Maps_Select_B[i]);
                ratingBar.setRating((i / 3) + 1);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        music.pauseBGM();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        music.playBGM(SelectMap.this, R.raw.music_select_map_or_player);
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
