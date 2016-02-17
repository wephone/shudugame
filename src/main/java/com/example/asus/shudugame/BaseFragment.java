package com.example.asus.shudugame;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ASUS on 2015/12/12.
 */
public class BaseFragment extends Fragment {
    @SuppressWarnings("unchecked") public <E extends BaseFragment> E setArgument(Bundle args) {
        this.setArguments(args);
        return (E)this;
    }

    @SuppressWarnings("unchecked") public <E extends View> E getView(View v, int resId) {
        return (E) v.findViewById(resId);
    }

    public Context getContext(){
        return getActivity();
    }

    public void T(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

}
