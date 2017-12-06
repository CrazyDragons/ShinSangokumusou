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

import static com.hzw.shinsangokumusou.R.drawable.maps_cb;
import static com.hzw.shinsangokumusou.R.drawable.maps_cbp;
import static com.hzw.shinsangokumusou.R.drawable.maps_cd;
import static com.hzw.shinsangokumusou.R.drawable.maps_djs;
import static com.hzw.shinsangokumusou.R.drawable.maps_fc;
import static com.hzw.shinsangokumusou.R.drawable.maps_gd;
import static com.hzw.shinsangokumusou.R.drawable.maps_gy;
import static com.hzw.shinsangokumusou.R.drawable.maps_hf;
import static com.hzw.shinsangokumusou.R.drawable.maps_hfxc;
import static com.hzw.shinsangokumusou.R.drawable.maps_hj;
import static com.hzw.shinsangokumusou.R.drawable.maps_hlg;
import static com.hzw.shinsangokumusou.R.drawable.maps_init;
import static com.hzw.shinsangokumusou.R.drawable.maps_jt;
import static com.hzw.shinsangokumusou.R.drawable.maps_lb;
import static com.hzw.shinsangokumusou.R.drawable.maps_nm;
import static com.hzw.shinsangokumusou.R.drawable.maps_st;
import static com.hzw.shinsangokumusou.R.drawable.maps_tg;
import static com.hzw.shinsangokumusou.R.drawable.maps_wc;
import static com.hzw.shinsangokumusou.R.drawable.maps_wj;
import static com.hzw.shinsangokumusou.R.drawable.maps_wzy;
import static com.hzw.shinsangokumusou.R.drawable.maps_yl;

public class SelectMap extends AppCompatActivity {

    private Music music;
    private MediaPlayer mediaPlayer;
    private ListView listView;
    private ImageView showmap;
    private TextView text_map_name, text_map_yaer, VS_A, VS_B;
    private RadioButton select_A, select_B;
    private RatingBar ratingBar;
    private Button map_ok;

    private String[] maps_name = {
            "黄巾之乱", "虎牢关之战", "刘表奇袭战", "宛城之战",
            "吴郡攻略战", "官渡之战", "关羽千里行", "长板之战",
            "赤壁之战", "成都压制战", "潼关之战", "合肥之战",
            "樊城之战", "定军山之战", "夷陵之战", "南蛮平定战",
            "街亭之战", "石亭谋略战", "合肥新城包围战", "五丈原之战"
    };
    private int[] maps_images = {
            maps_hj, maps_hlg, maps_lb,maps_wc,
            maps_wj, maps_gd, maps_gy, maps_cbp,
            maps_cb, maps_cd, maps_tg, maps_hf,
            maps_fc, maps_djs, maps_yl, maps_nm,
            maps_jt, maps_st, maps_hfxc, maps_wzy
    };

    private String[] maps_yaer = {
            "公元184年", "公元191年", "公元192年", "公元197年",
            "公元197年", "公元200年", "公元201年", "公元208年",
            "公元208年", "公元214年", "公元211年", "公元215年",
            "公元219年", "公元219年", "公元222年", "公元225年",
            "公元228年", "公元228年", "公元234年", "公元234年"
    };

    private String[] maps_select_A = {
            "联合军", "联合军", "孙坚军", "曹操军",
            "孙策军", "曹操军", "关羽军", "刘备军",
            "联合军", "刘备军", "联合军", "曹操军",
            "关羽军", "刘备军", "刘备军", "诸葛亮军",
            "诸葛亮军", "孙权军", "孙权军", "诸葛亮军"
    };
    
    private String[] maps_select_B = {
            "黄巾军", "董卓军", "刘表军", "张绣军",
            "联合军", "袁绍军", "曹操军", "曹操军",
            "曹操军", "刘璋军", "曹操军", "孙权军",
            "孙权军", "曹操军", "孙权军", "南蛮军",
            "司马懿军", "司马懿军", "司马懿军", "司马懿军"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_map);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();

    }

    public void init(){

        music = new Music(mediaPlayer);
        music.playBGM(SelectMap.this, R.raw.select_map);

        showmap = (ImageView) findViewById(R.id.image_show_map);
        showmap.setImageResource(maps_init);

        text_map_name = (TextView) findViewById(R.id.text_map_name);
        text_map_yaer = (TextView) findViewById(R.id.text_map_yaer);
        VS_A = (TextView) findViewById(R.id.VS_A);
        VS_B = (TextView) findViewById(R.id.VS_B);
        select_A = (RadioButton) findViewById(R.id.select_A);
        select_B = (RadioButton) findViewById(R.id.select_B);

        ratingBar = (RatingBar) findViewById(R.id.map_rating);

        map_ok = (Button) findViewById(R.id.map_ok);
        map_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectMap.this, SelectPlayer.class));
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.listview_map);
        listView.setAdapter(new ArrayAdapter<String>(SelectMap.this, android.R.layout.simple_list_item_1, maps_name));
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showmap.setImageResource(maps_images[i]);
                text_map_name.setText(maps_name[i]);
                text_map_yaer.setText(maps_yaer[i]);
                VS_A.setText(maps_select_A[i]);
                VS_B.setText(maps_select_B[i]);
                select_A.setText(maps_select_A[i]);
                select_B.setText(maps_select_B[i]);
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
        music.playBGM(SelectMap.this, R.raw.select_map);
    }
}
