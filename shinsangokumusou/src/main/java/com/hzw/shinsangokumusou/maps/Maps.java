package com.hzw.shinsangokumusou.maps;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.hzw.shinsangokumusou.R;
import com.hzw.shinsangokumusou.chess.Chess;
import com.hzw.shinsangokumusou.chess.allchess.General;
import com.hzw.shinsangokumusou.chess.allchess.Player;
import com.hzw.shinsangokumusou.database.DataBase;
import com.hzw.shinsangokumusou.diaplay.BaseDisplay;
import com.hzw.shinsangokumusou.interfaces.DBUtils;
import com.hzw.shinsangokumusou.maps.allmap.HJmap;
import com.hzw.shinsangokumusou.music.BGM;
import com.hzw.shinsangokumusou.staticvalue.MapsValue;
import com.hzw.shinsangokumusou.staticvalue.SQLiteValue;
import com.hzw.shinsangokumusou.utils.IOUtils;
import com.hzw.shinsangokumusou.utils.LogUtil;
import com.hzw.shinsangokumusou.utils.MapsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import static com.hzw.shinsangokumusou.chess.Chess.getRad;
import static com.hzw.shinsangokumusou.utils.MapsUtils.GetPosition;

public class Maps extends BaseDisplay implements View.OnTouchListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener , DBUtils{

    private RelativeLayout mapRL;
    private HJmap mapview;
    private ScrollView scrollView;
    private HorizontalScrollView horizontalScrollView;
    private RelativeLayout relativeLayout;
    private RelativeLayout listView;
    private Player playerview;
    private SeekBar seekBar;
    private Button save;
    private float oldDist;
    private int TouthCnt;
    private float multiple = 1;
    private Handler handler;
    private int[][] maps;
    MediaPlayer mediaPlayer;
    BGM BGM = new BGM(mediaPlayer);

    boolean change = false;
    private float rad;
    private List<Chess> chessList;
    private SQLiteDatabase sqLiteDatabase;
    private int name = 0;
    private int moving = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        init();
    }

    public void init() {

        scrollView = (ScrollView) findViewById(R.id.sv);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv);
        mapRL = (RelativeLayout) findViewById(R.id.mapview);
        relativeLayout = (RelativeLayout) findViewById(R.id.messageRL);
        listView = (RelativeLayout) findViewById(R.id.messageLV);
        seekBar = (SeekBar) findViewById(R.id.seekbar);

        mapview = new HJmap(this);

        mapview.setId(R.id.HJ_Map);

        maps = mapview.getMaps();

        chessList = getChessList();
        mapRL.addView(mapview);
        for (int i = 0; i < chessList.size(); i++) {
            mapRL.addView(chessList.get(i));
        }

        mapview.setBackgroundColor(MapsValue.Ground);

        mapview.setOnTouchListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        relativeLayout.setOnClickListener(this);
        listView.setOnClickListener(this);

        BGM.playBGM(Maps.this, R.raw.music_hj_map);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IOUtils.Save_MapArray(maps, getApplicationContext());
            }
        });

    }

    public List<Chess> getChessList(){

        ArrayList<Chess> chessList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            General general = new General(this, i);
            general.setOldW(3 * i * MapsValue.Eachmap);
            general.setOldH(3 * i * MapsValue.Eachmap);
            LogUtil.args_2("ppp", "原X: ", general.getOldW(), "\n原Y： ", general.getOldH());
            general.SetGeneralPosition(GetPosition(general.getOldW()), GetPosition(general.getOldH()), multiple);
            chessList.add(general);
        }

        for (int i = 10; i < 15; i++) {
            Player player = new Player(this, i);
            player.setOldW(3 * i * MapsValue.Eachmap);
            player.setOldH(3 * i * MapsValue.Eachmap);
            player.SetGeneralPosition(GetPosition(player.getOldW()), GetPosition(player.getOldH()), multiple);
            chessList.add(player);
        }

        Cursor cursor = getCursor(SQLiteValue.Query_Count_Chess, null);
        while (cursor.moveToNext()){
            int count = cursor.getInt(0);
            if (count == 0){
                for (int i = 0; i < 15; i++) {
                    InsertDB(SQLiteValue.Insert_Chess, new Object[]{null, i+"", 0, 1});
                }
            }
        }
        closeCursor(cursor);
        closeDB();

        return chessList;
    }

    /**
     * 设置缩放
     *
     * @param mapview  地图
     * @param chess    棋
     * @param multiple 倍率
     */
    public void SetZoom(HJmap mapview,  List<Chess> chesslist, float multiple) {

        /******* 注意：这里一定不要把1080， 1350替换成MapsValue.getMap_width()，MapsValue.getMap_height()*******/
        MapsValue.setMap_width((int) (MapsValue.Map_width * multiple));
        MapsValue.setMap_height((int) (MapsValue.Map_height * multiple));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                (int) (MapsValue.getMap_width()),
                (int) (MapsValue.getMap_height()));
        mapview.setLayoutParams(params);

        LogUtil.args_4("test", "srcoll宽： ", scrollView.getWidth(), " scroll高： ", scrollView.getHeight(),
                "\nmap宽： ", MapsValue.getMap_width(), "map高：", MapsValue.getMap_height());

        mapview.setScaleX(multiple);
        mapview.setScaleY(multiple);
        mapview.setPivotX(0);
        mapview.setPivotY(0);
        mapview.invalidate();

        /*if (newW == 0 && newH == 0){
            chess.SetPlayerPosition(4, 4, multiple);
        }else {

        }
        LogUtil.args_2("ggg", "X： ", chess.getX(), " ，Y：", chess.getY());*/

            for (int i = 0; i < chesslist.size(); i++) {
                if ((chesslist.get(i).getNewW() == 0) && (chesslist.get(i).getNewH() == 0)){
                    chesslist.get(i).SetPlayerPosition(GetPosition(chesslist.get(i).getOldW()), GetPosition(chesslist.get(i).getOldH()), multiple);
                }else {
                    chesslist.get(i).SetPlayerPosition(GetPosition(chesslist.get(i).getNewW()), GetPosition(chesslist.get(i).getNewH()), multiple);
                }
                chesslist.get(i).invalidate();
//                LogUtil.args_2("ppp", "X: ", chesslist.get(i).getNewW(), "\nY： ", chesslist.get(i).getNewH());
            }



        handler = new Handler();
        handler.postDelayed(runnable, 200);
    }

    public int getWhichChess(){

        Cursor cursor = getCursor(SQLiteValue.Query_Chess_Name, null);
        Log.d("qqq", "正在进入查询: ");
        while (cursor.moveToNext()){
            Log.d("qqq", "进入cursor: ");
            name = cursor.getInt(1);
            Log.d("qqq", "正在查询: "+name);
        }
        cursor.close();
        return name;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()){

            case R.id.HJ_Map:
                int nCnt = motionEvent.getPointerCount();

                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:

                        Cursor cursor = getDB().rawQuery(SQLiteValue.Query_Chess_Name, null);
                        while (cursor.moveToNext()) {
                            moving = cursor.getInt(2);
                        }
                        cursor.close();

                        if (moving == 0){

                        }else {
                            chessList.get(getWhichChess()).setMoving(false);
                            chessList.get(getWhichChess()).setComplete(false);
                            LogUtil.args_2("xxx", "（在map按down时）   是否完成： ", chessList.get(getWhichChess()).isComplete(), "， 是否移动： ", chessList.get(getWhichChess()).isMoving());
                        }
                        break;


                    case MotionEvent.ACTION_UP:


                        /*ToastUtil.args_4(Maps.this,
                                "", ToastUtil.X_Y("点击", motionEvent.getX(), motionEvent.getY()),
                                "", ToastUtil.X_Y("数组", GetPosition(motionEvent.getX() - 1), GetPosition(motionEvent.getY())),
                                "倍数:   ", multiple,
                                "该位置参数:   ", maps[GetPosition(motionEvent.getX()) - 1][GetPosition(motionEvent.getY()) - 1]);*/

                        LogUtil.args_2("xxx", "（在map准备按up时）   是否完成： ", chessList.get(getWhichChess()).isComplete(), "， 是否移动： ", chessList.get(getWhichChess()).isMoving());

                        if (!chessList.get(getWhichChess()).isMoving() && !chessList.get(getWhichChess()).isComplete() && (moving == 1)) {
                            chessList.get(getWhichChess()).setNewW(motionEvent.getX());
                            chessList.get(getWhichChess()).setNewH(motionEvent.getY());
                           /* if (newW == 0 && newH == 0){
                                chessList.get(getWhichChess()).setOldW(5 * 15);
                                chessList.get(getWhichChess()).setOldH(6 * 15);
                            }*/
                            rad = getRad((GetPosition(chessList.get(getWhichChess()).getNewW()) - ((GetPosition(chessList.get(getWhichChess()).getOldW()) - 1))), ((GetPosition(chessList.get(getWhichChess()).getOldH())) - (GetPosition(chessList.get(getWhichChess()).getNewH()))));

                            LogUtil.args_5("rrr", "角度: ", rad, "\n原X：", (GetPosition(chessList.get(getWhichChess()).getOldW()) - 1)," , 原Y： ", GetPosition(chessList.get(getWhichChess()).getOldH()),
                                    "\n现X：", GetPosition(chessList.get(getWhichChess()).getNewW()), " , 现Y： ", GetPosition(chessList.get(getWhichChess()).getNewH()));
                            chessList.get(getWhichChess()).setRad(rad);

                            LogUtil.args_1("ccc", "要准备对这个棋子移动了： ", getWhichChess());

                            chessList.get(getWhichChess()).SetGeneralPosition(GetPosition(motionEvent.getX()), GetPosition(motionEvent.getY()), multiple);
                            chessList.get(getWhichChess()).invalidate();
                            // TODO: 2017/12/12 21:20 可以写写timer的取消（虽然现在不写也没有发现什么问题）
                            chessList.get(getWhichChess()).getTimer().cancel();
                            chessList.get(getWhichChess()).setVisibility(View.VISIBLE);
                            chessList.get(getWhichChess()).setComplete(true);
//                            maps[GetPosition(newW) - 1][GetPosition(newH) - 1] = 1;

                            UpdateDB("update test_data set Moving = ? where id = 1", new Object[]{0});

                            LogUtil.args_2("xxx", "（在chess移动后）   是否完成： ", chessList.get(getWhichChess()).isComplete(), "， 是否移动： ", chessList.get(getWhichChess()).isMoving());

                        }else {
                            Toast.makeText(Maps.this, "请点击旗子", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = MapsUtils.Spacing(motionEvent);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        /*if (nCnt == 2){
                            float newDist = Spacing(motionEvent);
                            if (newDist > oldDist + 1) {
                                oldDist = newDist;
                                multiple *= 2;
                                if (multiple >= 1 && multiple <=8){
                                    LogUtil.d("ppp", "倍数： "+multiple);
                                    SET(mapview, chessView, multiple);
                                }else {
                                    LogUtil.d("xxx", "onTouch: ");
                                }
                            }
                            if (newDist < oldDist - 1) {
                                oldDist = newDist;
                                multiple /= 2;
                                if (multiple >= 1 && multiple <=8){
                                    LogUtil.d("ppp", "倍数： "+multiple);
                                    SET(mapview, chessView, multiple);
                                }else {
                                    LogUtil.d("xxx", "onTouch: ");
                                }
                            }
                        }
                        break;*/
                    default:
                        break;
                }
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.messageRL:
                SetZoom(mapview, chessList, 1f);
                multiple = 1;
                break;
            case R.id.messageLV:
                SetZoom(mapview, chessList, 8f);
                multiple = 8;
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.seekbar:
                //滑动条放大地图1-8倍
                multiple = 1 + 0.07f * i;
                SetZoom(mapview, chessList, multiple);
                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        BGM.pauseBGM();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        BGM.playBGM(Maps.this, R.raw.music_hj_map);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BGM.stopBGM();
    }

    @Override
    protected void onStop() {
        super.onStop();
        BGM.stopBGM();
    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            //每次缩放视图置于屏幕中心
            if (multiple != 1) {
                /******* 注意：这里一定不要把1080， 1350替换成MapsValue.getMap_width()，MapsValue.getMap_height()*******/
                scrollView.scrollTo(0, (int) (MapsValue.Map_height * multiple * 0.5 * (1 - 1 / multiple)));
                horizontalScrollView.scrollTo((int) (MapsValue.Map_width * multiple * 0.5 * (1 - 1 / multiple)), 0);
            }
        }
    };

    @Override
    public SQLiteDatabase getDB() {
        sqLiteDatabase = new DataBase(Maps.this).getWritableDatabase();
        return sqLiteDatabase;
    }

    @Override
    public Cursor getCursor(String sql, String[] strings) {
        return getDB().rawQuery(sql, strings);
    }

    @Override
    public void InsertDB(String sql, Object[] objects) {
        getDB().execSQL(sql, objects);
    }

    @Override
    public void UpdateDB(String sql, Object[] objects) {
        getDB().execSQL(sql, objects);
    }

    @Override
    public void closeCursor(Cursor cursor) {
        cursor.close();
    }

    @Override
    public void closeDB() {
        getDB().close();
    }
}
