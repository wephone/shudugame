package com.example.asus.shudugame;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import master.SelectLevel;
import master.SudokuList;

/**
 * Created by ASUS on 2015/12/21.
 */
public class Welcome extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.welcome,container,false);
        view.findViewById(R.id.bj).setOnClickListener(this);
        view.findViewById(R.id.lbj).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (getId()){
            case R.id.bj:
                ((MainActivity)getActivity()).setMainFragment(new  SelectLevel());
                break;
            case R.id.lbj:
                ((MainActivity)getActivity()).setMainFragment(new  SelectLevel());
                break;
            default:
                break;
        }

    }
}
