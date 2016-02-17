package master;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.shudugame.MainActivity;
import com.example.asus.shudugame.R;
import com.example.asus.shudugame.U;
import com.example.asus.shudugame.shuduku;
import com.example.asus.shudugame.table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2015/12/13.
 */
public class SelectDialog extends Dialog {
    private int mPosition;
    private int[][] curMatrix = new int[table.ROW][table.ROW];
    public int nums;
    private List<Integer> pendingNumber = new ArrayList<Integer>();//定义整形数组集合

    private onNumberSelectListener listener;

//dialog初始化
    public SelectDialog(Context context) {
        this(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
    }
    public SelectDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    private int[][] numbers = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

    private GridView gv;
    private GridView gv9;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_select);
        gv = (GridView) findViewById(R.id.gv3);//嘿嘿嘿选了个gv3就可以输出了!!
        gv9= (GridView) findViewById(R.id.gv);
    }
    @Override protected void onStart() {
        super.onStart();
        Log.d("debug", "onstart..");
        gv.setAdapter(new SelectAdapter());
    }
    public void show(int[][] curMatrix, int position) {//传进来的position是外层game的单元格位置
        Log.d("debug", "show..");
        super.show();
        this.mPosition = position;//外层单元格位置
//        U.copyMatrix(this.curMatrix, curMatrix);
//        this.curMatrix[position / table.ROW][position % table.ROW] = 0;//外面的那一行列为0
//        pendingNumber.clear();
//        shuduku sudoku = new shuduku();
//        table.PendingNode n = null;//在table类里 table应该是制表的意思是
//        try {
//            n = sudoku.init(this.curMatrix).getPendingNode(
//                    position / table.ROW, position % table.ROW);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (n != null) {
//            for(Integer num :n.getPendingList()){//（元素类型 元素变量：遍历对象）
//                pendingNumber.add(num);
//            }
//            Log.d("debug", pendingNumber.toString());
//        }
    }
    public void setOnNumberSelectListener(onNumberSelectListener l){
        this.listener = l;
    }

    public class SelectAdapter extends BaseAdapter{
    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int position) {
        return numbers[position / 3][position % 3];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = getLayoutInflater().inflate(
                R.layout.item_select_number, null);
        ((TextView) convertView.findViewById(R.id.tv_number)).setText(""
                + (position+1));//设置对话框1-9


        convertView.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View arg0) {
//                String A= String.valueOf(position);
//                Toast.makeText(getContext(), A, Toast.LENGTH_SHORT).show();
                if(listener!=null){
                    listener.onSelect(mPosition,position+1);//传mposition和0给onselect,再在game里调用，达到传值通信
                }
                dismiss();

            }
        });
        return convertView;
    }

}
    public interface onNumberSelectListener{
        public void onSelect(int position,int number);
    }
}
