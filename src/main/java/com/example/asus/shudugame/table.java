package com.example.asus.shudugame;

import java.util.List;

/**
 * Created by ASUS on 2015/12/13.
 */
public class table {
    public final static int ROW=9;
    private int[][] cur_table = new int[ROW][ROW];

    public class PendingNode {//设置未确定的节点
        private int x;
        private int y;
        private List<Integer> pendingList;
        public PendingNode(int x, int y, List<Integer> pendingList) {
            this.x = x;
            this.y = y;
            this.pendingList = pendingList;
        }
        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
        public List<Integer> getPendingList() {
            return pendingList;
        }
        public void setPendingList(List<Integer> pendingList) {
            this.pendingList = pendingList;
        }
        @Override public String toString() {
            return "PendingNode [pendingList=" + pendingList.toString()
                    + ", x=" + x + ", y=" + y + "]";
        }
    }//将xy转化为字符
}
