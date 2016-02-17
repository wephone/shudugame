package com.example.asus.shudugame;



import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import master.SelectLevel;
import master.introduc;

public class MainActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        setMainFragment(new Welcome());
        setMainFragment(new SelectLevel());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void setMainFragment(Fragment fragment) {
        FragmentManager fragmentManager=getFragmentManager();//获取fragment管理者
        FragmentTransaction beginTransaction =fragmentManager.beginTransaction(); //开启事务
        beginTransaction.replace(R.id.container,fragment);
        beginTransaction.commit();
    }
//    public void out(Fragment fragment){
//        FragmentManager fragmentManager=getFragmentManager();//获取fragment管理者
//        FragmentTransaction beginTransaction =fragmentManager.beginTransaction(); //开启事务
//        beginTransaction.remove(R.id.out,fragment);
//        beginTransaction.commit();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_start:

//                SelectLevel selectLevel=new SelectLevel();
//                FragmentManager fragmentManager=getFragmentManager();//获取fragment管理者
//                FragmentTransaction beginTransaction =fragmentManager.beginTransaction(); //开启事务
//                beginTransaction.add(R.id.main,selectLevel);//
//                beginTransaction.addToBackStack(null);
//                beginTransaction.commit();
                setMainFragment(new SelectLevel());
                break;

            case R.id.action_intro:
                setMainFragment(new introduc());

                break;
            case R.id.action_about:

                break;
            default:
                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
