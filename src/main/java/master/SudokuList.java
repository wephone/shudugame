package master;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.shudugame.BaseFragment;
import com.example.asus.shudugame.Game;
import com.example.asus.shudugame.MainActivity;
import com.example.asus.shudugame.R;
import com.example.asus.shudugame.U;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ASUS on 2015/12/12.
 */
public class SudokuList extends BaseFragment{
    private int lv;
    private int size = 1;
    private ListView listView;
    private SudokuListAdapter adapter;

    public static SudokuList newInstance(int lv) {
        Bundle bundle = new Bundle();
        bundle.putInt("lv", lv);//把lv传进数据包
        return new SudokuList().setArgument(bundle);
        //发信息
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {//接信息
            lv = getArguments().getInt("lv");
        } else {
            lv = 1;
        }//传递选择到的等级
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sudokulist, container, false);
        try {
            JSONObject jsonObject = new JSONObject(U.readJsonFile(getContext(), lv));
            size = jsonObject.getInt("size");//试过了 try失败，跑不成功 直接跳转3，不可以直接copy ecilpse里的文件，要自己创建一个assetFolder
        } catch (JSONException e) {
            e.printStackTrace();
            size = 3;//这里本来是1
        }
        listView  =getView(v, R.id.lv_sudokulist);
        adapter  =new SudokuListAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override public void onItemClick(AdapterView<?> parent, View view,
                                              int position, long id) {
                ((MainActivity)getActivity()).setMainFragment(Game.newInstance(lv, position + 1));//传递难度和等级给game
            }
        });
        return v;
    }

    private class SudokuListAdapter extends BaseAdapter {

        @Override public int getCount() {

//            return size;
            return size;
        }

        @Override public Object getItem(int position) {

            return position;
        }

        @Override public long getItemId(int position) {

            return position;
        }

        @Override public View getView(int position, View convertView,
                                      ViewGroup parent) {
            ViewHodler h = null;
            if(convertView==null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sudokulist, null);
                h = new ViewHodler();
                h.tv_num  = (TextView)convertView.findViewById(R.id.tv_num);
                convertView.setTag(h);
            }else{
                h = (ViewHodler)convertView.getTag();
            }

            h.tv_num.setText("No."+(position+1));
            return convertView;
        }


        class ViewHodler {
            TextView tv_num;
        }
    }
}
