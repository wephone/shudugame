package com.example.asus.shudugame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ASUS on 2015/12/12.
 */
public class U {
    private static final String Sudoku_Folder = "sudoku" + File.separator;//分离器？保存路径？
    private static final String Suffix = ".su";

    public static void copyMatrix(int[][] to, int[][] from) {
        int row = from.length;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < row; j++)
                to[i][j] = from[i][j];
    }//复制数组
    public static int[][] readMatrix(Context context, int lv, int num) {//这段应该是读取制作各个数组的意思,制作各个等级数组，用字符串编码再转为十进制数字,不可以直接读取所以用字节流字符流制作读取
        int[][] rawMatrix = new int[table.ROW][table.ROW];
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets()//inputstream抽象了程序读取数据的方式
                    .open(Sudoku_Folder + lv + File.separator + num + Suffix)));
            String lineString = null;
            int line = 0;
            while ((lineString = br.readLine()) != null) {
                System.out.println(lineString);
                String[] s = lineString.trim().split(" ");//返回一个下标从零开始的一维数组，它包含传回数组= Split(原始字串, 要找的字串, 拆成几个数组)
                //默认返回一维数组，以指定字符分割，T=split("F:\a\a.txt","\")
                //则：T(0)="F:";T(1)="a";T(2)="a.txt" ;T(UBound(T))=a.txt
                for (int j = 0; j < s.length; j++) {
                    rawMatrix[line][j] = Integer.parseInt(s[j]);//讲一个字符转化为一个十进制整形数字
                }
                line++;
                //只制作9×9格
                if(line == 9){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rawMatrix;
    }
    public static String readFile(InputStream in){
        String s = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(in));
            String lineString = null;
            while ((lineString = br.readLine()) != null) {
                s += lineString;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return s;
    }
    public static String readJsonFile(Context context, int lv) {
        String file_name = Sudoku_Folder + lv + File.separator + lv + ".json";
        String json = "";
        try {
            return readFile(context.getAssets()
                    .open(file_name));
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
    }
    public static String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);   //鍒ゆ柇sd鍗℃槸鍚﹀瓨鍦�
        if   (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//鑾峰彇璺熺洰褰�
        }
        return sdDir.toString();
    }

    public static String getAppSDPath(){
        String path = getSDPath() + File.separator + "Sudoku" +File.separator;
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        return path;
    }

    public static String getScreenShotPath(){
        return getAppSDPath() + "screenshot.png";
    }
    /**
     * 鎴彇view瑙嗗浘
     * @param v
     */
    public static void screenShot(View v) {
        Bitmap bmp  = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        v.draw(new Canvas(bmp ));

        String fname = getScreenShotPath();
        try {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(fname));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
