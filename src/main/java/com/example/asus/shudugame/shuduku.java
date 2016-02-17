package com.example.asus.shudugame;

import android.widget.Toast;

/**
 * Created by ASUS on 2015/12/13.
 */
public class shuduku {
    /** 鏁扮嫭琛� */
    table table;
    /** 寰呭～鐐� */
//    List<PendingNode> pendingNodes;
    /** 寰呭～鐐� */
    boolean debug = false;
    /** 璧峰鏃堕棿 */
    long startTime;
    long costTime;

    int[][] result = new int[table.ROW][table.ROW];

    public shuduku() {
//        this(false);
    }

    //检测是否为空
    public boolean check(int[][] cur_table) {
        try {
            for (int i = 0; i < table.ROW; i++)
                for (int j = 0; j < table.ROW; j++)
                    if (cur_table[i][j] == 0) {
                        return false;
                    }//算法跪了T-T

//            new table(cur_table);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
//    public shuduku init(int[][] cur_Matrix) throws Exception {//抛出异常
//        table = new table(cur_Matrix);
//        pendingNodes = table.getPendingNodes();
//        if (debug) {
//            for (com.example.asus.shudugame.table.PendingNode node : table.getPendingNodes()) {
//                System.out.println(node.toString());
//            }
//        }
//
//        return this;
//    }
    public boolean check2(int[][]cur_table){//行
        for (int i = 0; i < table.ROW; i++){
            for (int a = 1,b=0; a < 10; a++) {
                for (int j = 0; j < table.ROW; j++) {
                    if (cur_table[i][j]==a){
                        b++;
                        if (b>1){
                            return false;
                        }
                    }else continue;
                }
                b=0;
            }
        }
        for (int j = 0; j < table.ROW; j++){//列
            for (int a = 1,b=0; a < 10; a++) {
                for (int i = 0; i < table.ROW; i++) {
                    if (cur_table[i][j]==a){
                        b++;
                        if (b>1){
                            return false;
                        }
                    }else continue;
                }
                b=0;
            }
        }
        for (int i = 2; i < table.ROW; i=i+3) {
            for (int j = 2; j <table.ROW ; j=j+3) {
                for (int a = 1,b=0; a <10 ; a++) {
                    for (int m = i; m>i-2 ; m--) {
                        for (int n = j; n>j-2; n--) {
                            if (cur_table[m][n]==a){
                                b++;
                                if (b>1){
                                    return false;
                                }
                            }else continue;
                        }
                    }
                    b=0;
                }
            }
        }


        return true;
    }
}
