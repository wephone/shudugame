package master;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.shudugame.MainActivity;
import com.example.asus.shudugame.R;

/**
 * Created by ASUS on 2015/12/12.
 */
public class SelectLevel extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.select,container,false);
        view.findViewById(R.id.btn_lv1).setOnClickListener(this);
        view.findViewById(R.id.btn_lv2).setOnClickListener(this);
        view.findViewById(R.id.btn_lv3).setOnClickListener(this);
        view.findViewById(R.id.btn_lv4).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_lv1:
//			((Main)getActivity()).setMainFragment(Game.newInstance(1, 2));
                ((MainActivity)getActivity()).setMainFragment(SudokuList.newInstance(1));
                break;
            case R.id.btn_lv2:
//			((Main)getActivity()).setMainFragment(Game.newInstance(2, 2));
                ((MainActivity)getActivity()).setMainFragment(SudokuList.newInstance(2));
                break;
            case R.id.btn_lv3:
//			((Main)getActivity()).setMainFragment(Game.newInstance(3, 2));
                ((MainActivity)getActivity()).setMainFragment(SudokuList.newInstance(3));
                break;
            case R.id.btn_lv4:
//			((Main)getActivity()).setMainFragment(Game.newInstance(4, 2));
                ((MainActivity)getActivity()).setMainFragment(SudokuList.newInstance(4));//将等级界面传递进去
                break;
            default:
                break;
        }


    }
}
