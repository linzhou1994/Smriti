package com.linzhou.smriti.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.linzhou.smriti.Base.BaseWebSocketActivity;
import com.linzhou.smriti.R;

import org.json.JSONObject;

public class MainActivity extends BaseWebSocketActivity implements View.OnClickListener {

    /**
     * 底部的四个布局文件
     */
    private LinearLayout mbottom1;
    private LinearLayout mbottom2;
    private LinearLayout mbottom3;
    private LinearLayout mbottom4;
    /**
     * 底部的四个ImageButton
     */
    private ImageButton mImgbtm1;
    private ImageButton mImgbtm2;
    private ImageButton mImgbtm3;
    private ImageButton mImgbtm4;

    private Fragment mf1;
    private Fragment mf2;
    private Fragment mf3;
    private Fragment mf4;

    @Override
    protected int getlayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initview() {
        mfindviewbyid();
        initialization();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        super.setListener();
        mbottom1.setOnClickListener(this);
        mbottom2.setOnClickListener(this);
        mbottom3.setOnClickListener(this);
        mbottom4.setOnClickListener(this);
    }

    private void initialization() {
        setSelect(0);
    }

    private void mfindviewbyid() {
        mbottom1 = (LinearLayout) findViewById(R.id.main_map);
        mbottom2 = (LinearLayout) findViewById(R.id.main_bottom2);
        mbottom3 = (LinearLayout) findViewById(R.id.main_bottom3);
        mbottom4 = (LinearLayout) findViewById(R.id.main_bottom4);
        mImgbtm1 = (ImageButton) findViewById(R.id.main_map_img);
        mImgbtm2 = (ImageButton) findViewById(R.id.main_bottom2_img);
        mImgbtm3 = (ImageButton) findViewById(R.id.main_bottom3_img);
        mImgbtm4 = (ImageButton) findViewById(R.id.main_bottom4_img);
    }



    @Override
    public void onClick(View v) {
        resetImgs();//初始化图片
        switch (v.getId()) {
            case R.id.main_map:
                setSelect(0);
                //调用select方法，改变颜色显示对应fragment
                break;
            case R.id.main_bottom2:
                setSelect(1);
                break;
            case R.id.main_bottom3:
                setSelect(2);
                break;
            case R.id.main_bottom4:
                setSelect(3);
                break;
            default:
                break;
        }
    }


    public void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 开启Fragment事务
        hideFragment(transaction);  //隐藏所有的Fragment
        //设置选中区域是亮的
        switch (i) {
            case 0:
                if (mf1 == null)             //如果没有显示过
                {
                    //mf1=new MapFragment();
                    transaction.add(R.id.id_content, mf1);
                    // 往Activity中添加一个Fragment,这里添加的是MenuFragment
                } else {
                    transaction.show(mf1);
                    //显示之前隐藏的Fragment
                }
                //mImgbtm1.setImageResource(R.mipmap.dingwei2);
                break;

            default:
                break;
        }
        transaction.commit();
        //提交一个事务
    }


    private void hideFragment(FragmentTransaction transaction) {
        if (mf1 != null) {
            transaction.hide(mf1);//隐藏这个Fragment
        }
        if (mf2 != null) {
            transaction.hide(mf2);
        }
        if (mf3 != null) {
            transaction.remove(mf3);
        }
        if (mf4 != null) {
            transaction.hide(mf4);
        }

    }


    private void resetImgs() {
        // mImgbtm1.setImageResource(R.mipmap.dingwei1);

    }

    @Override
    public void remessage(String resulttype,JSONObject mes) {

    }
}
