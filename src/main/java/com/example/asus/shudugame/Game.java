package com.example.asus.shudugame;

import android.app.AlertDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import master.LoadDialog;
import master.SelectDialog;
import master.TimeRecorderTextView;

/**
 * Created by ASUS on 2015/12/13.
 */
public class Game extends BaseFragment implements SelectDialog.onNumberSelectListener {
    private int lv, num;
    private MediaPlayer mMediaPlayer;
    GridView gv;
    GvAdapter adapter;
    SelectDialog selectDialog;
    LoadDialog loadDialog;
    TimeRecorderTextView timeRecorder;
    long record_time = 0;
    int[][] rawMatrix = new int[table.ROW][table.ROW];//原矩阵
    int[][] curMatrix = new int[table.ROW][table.ROW];//副矩阵


    public static Game newInstance(int lv, int num) {
        Bundle args = new Bundle();
        args.putInt("lv", lv);
        args.putInt("num", num);
        return new Game().setArgument(args);
    }//传递点击的难度和难度对应的级别

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lv = getArguments().getInt("lv", 1);//1只是默认值，缺省值
            num = getArguments().getInt("num", 1);
        } else {
            lv = num = 1;
        }
        // 閫氱煡绯荤粺鍒锋柊杩欓噷鐨凮ptionMenu
        setHasOptionsMenu(true);

    }//有点看不懂

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);
        selectDialog = new SelectDialog(getActivity());
        selectDialog.setOnNumberSelectListener(this);//接口传值通信好像需要获取上下文对象
//        loadDialog = new LoadDialog(getActivity());
        U.copyMatrix(rawMatrix, U.readMatrix(getContext(), lv, num));//分等级难度传递不同的值，U应该是存储各个等级对应的数组
        U.copyMatrix(curMatrix, rawMatrix);//这里应该是，把数据从U里传递过来，再将他们复制成两个相同的数组
        gv = getView(v, R.id.gv);
        timeRecorder = getView(v, R.id.tv_timerecord);
        timeRecorder.clean();
        adapter = new GvAdapter(getContext());
        gv.setAdapter(adapter);
        return v;
    }
    @Override public void onStart() {
        super.onStart();
        timeRecorder.startTiming();
    }

    @Override public void onPause() {
        super.onPause();
        timeRecorder.stopTiming();
    }

    @Override public void onDestroy() {
        super.onDestroy();
    }

    private boolean checkMatrix() {//检测是否满足正确
        shuduku sudoku = new shuduku();//suduku应该是数独内部算法
        if (sudoku.check(curMatrix)) {
            if (sudoku.check2(curMatrix)) {
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("成功解出! 用时:" + timeRecorder.getCostTime() + "s")
                        .setCancelable(true).create();
//                    .setNegativeButton("鍒嗕韩"
//                            new DialogInterface.OnClickListener() {
//
//                                @Override public void onClick(
//                                        DialogInterface arg0, int arg1) {
//                                    share();
//                                }
//                            }).create();  暂时不做分享
                dialog.show();
                timeRecorder.stopTiming();
                return true;
            } else {
                Toast.makeText(getContext(), "答案不对", Toast.LENGTH_SHORT).show();
            }
        } else {
            return false;
        }
        return false;
    }


    @Override
    public void onSelect(int position,int number) {
        try {
            curMatrix[position / table.ROW][position % table.ROW] = number;
//            new Sudoku().init(curMatrix);
            adapter.notifyDataSetInvalidated();
            if (checkMatrix());
//                musicFinish();
//            } else {
//                musicAddNumber();
//            }
        } catch (Exception e) {
            e.printStackTrace();
            curMatrix[position / table.ROW][position % table.ROW] = 0;
            adapter.notifyDataSetInvalidated();
            T("什么鬼!");
        }

    }


    class GvAdapter extends BaseAdapter{

        Context context;//为何获取上下文？

        public GvAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return table.ROW * table.ROW;
        }//为何每三个格子才一根粗线？

        @Override
        public Object getItem(int position) {
            return curMatrix[position / table.ROW][position % table.ROW];//数组的行列
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder h = null;
            if (convertView == null) {
                h = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.item_sudoku, null);
                h.tv_number = (TextView) convertView
                        .findViewById(R.id.tv_number);
                h.line_top = convertView.findViewById(R.id.line_top);
                h.line_left = convertView.findViewById(R.id.line_left);
                h.line_right = convertView.findViewById(R.id.line_right);
                h.line_bottom = convertView.findViewById(R.id.line_bottom);
                // final int _p = position;
                convertView.setTag(h);
            } else {
                h = (ViewHolder) convertView.getTag();
            }
            int row = position / table.ROW;//行列
            int column = position % table.ROW;//
            // background颜色处理,数组（默认值）数字为0，白色，不为零则为灰色
            if (curMatrix[row][column] == 0) {
                h.tv_number.setText("");
                h.tv_number.setBackgroundColor(context.getResources().getColor(
                        android.R.color.white));
            } else {
                h.tv_number.setText("" + curMatrix[row][column]);
                if (rawMatrix[row][column] != 0) {
                    h.tv_number.setBackgroundColor(context.getResources()
                            .getColor(R.color.grey));
                } else {
                    h.tv_number.setBackgroundColor(context.getResources()
                            .getColor(android.R.color.white));
                }
            }

             //line三行一深色
            if ((row + 1) % 3 != 0) {
                h.line_bottom.setVisibility(View.GONE);
            } else {
                h.line_bottom.setVisibility(View.VISIBLE);
            }
            if ((column + 1) % 3 != 0) {
                h.line_right.setVisibility(View.GONE);
            } else {
                h.line_right.setVisibility(View.VISIBLE);
            }
            if (rawMatrix[row][column] == 0) {
                if (rawMatrix[row][column] == 0) {//原生数组，不管你现在有没有填入，原生数组都可以弹出对话框，改变只变cur
                    convertView.setOnClickListener(new View.OnClickListener() {

                        @Override public void onClick(View arg0) {
                            selectDialog.show(curMatrix, position);//单元格的位置,弹出对话框
//                            selectDialog.show();
                        }
                    });
                }
            }
            return convertView;
        }
        class ViewHolder {
            TextView tv_number;
            View line_top, line_left, line_right, line_bottom;
        }

    }
}
