package com.hzw.shinsangokumusou.utils;

import android.content.Context;

import com.hzw.shinsangokumusou.staticvalue.MapsValue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * IO文件处理
 * Project ShinSangokumusou/com.hzw.shinsangokumusou.utils/IOUtils
 * Created by HZW
 * Data 2017/12/12
 * Time 11:05
 */

public class IOUtils {

    /**
     * 保存地图二维数组到手机
     * @param maps 二维数组
     * @param context 上下文
     */
    public static void Save_MapArray(int[][] maps, Context context) {

        try {

            File file = new File(context.getFilesDir().getParentFile().getPath()+"/files");
            if (!file.isFile()) {
                file.mkdir();
            }
            File out = new File(context.getFilesDir().getParentFile().getPath()+"/files/array.txt");
            FileWriter fileWriter = new FileWriter(out);
            for (int i = 0; i < MapsValue.MAX_X; i++) {
                for (int j = 0; j < MapsValue.MAX_Y; j++) {
                    fileWriter.write(maps[i][j] + "\t");
                }
                fileWriter.write("\r\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
