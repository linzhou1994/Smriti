package com.linzhou.smriti.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.linzhou.smriti.Base.BaseWebSocketActivity;
import com.linzhou.smriti.R;
import com.linzhou.smriti.fragment.CommunityFragment;
import com.linzhou.smriti.fragment.MessageFragment;
import com.linzhou.smriti.fragment.MyFragment;

import org.json.JSONObject;

public class MainActivity extends BaseWebSocketActivity implements View.OnClickListener {

    /**
     * 底部的四个布局文件
     */
    private LinearLayout mCommunityBt;
    private LinearLayout mMessageBt;
    private LinearLayout mMyBt;

    private ImageView mCommunityImg;
    private ImageView mMessageImg;
    private ImageView mMyImg;

    private Fragment mCommunity;
    private Fragment mMessage;
    private Fragment mMy;

    @Override
    protected int getlayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initview() {
        mfindviewbyid();

    }
    private void mfindviewbyid() {
        mCommunityBt = (LinearLayout) findViewById(R.id.community);
        mMessageBt = (LinearLayout) findViewById(R.id.message);
        mMyBt = (LinearLayout) findViewById(R.id.my);

        mCommunityImg= (ImageView) findViewById(R.id.img_community);
        mMessageImg = (ImageView) findViewById(R.id.img_message);
        mMyImg = (ImageView) findViewById(R.id.img_my);


    }





    @Override
    protected void initData() {
        //默认第一项
        setSelect(0);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mCommunityBt.setOnClickListener(this);
        mMessageBt.setOnClickListener(this);
        mMyBt.setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.community:
                setSelect(0);
                //调用select方法，改变颜色显示对应fragment
                break;
            case R.id.message:
                setSelect(1);
                //调用select方法，改变颜色显示对应fragment
                break;
            case R.id.my:
                setSelect(2);
                //调用select方法，改变颜色显示对应fragment
                break;
            default:
                break;
        }
    }


    public void setSelect(int i) {
        resetImgs();//初始化图片
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 开启Fragment事务
        hideFragment(transaction);  //隐藏所有的Fragment
        //设置选中区域是亮的
        switch (i) {
            case 0:
                if (mCommunity == null)             //如果没有显示过
                {
                    mCommunity=new CommunityFragment();
                    transaction.add(R.id.id_content, mCommunity);
                    // 往Activity中添加一个Fragment,这里添加的是MenuFragment
                } else {
                    transaction.show(mCommunity);
                    //显示之前隐藏的Fragment
                }
                mCommunityImg.setImageResource(R.mipmap.theme2);
                break;

            case 1:
                if (mMessage == null)             //如果没有显示过
                {
                    mMessage=new MessageFragment();
                    transaction.add(R.id.id_content, mMessage);
                    // 往Activity中添加一个Fragment,这里添加的是MenuFragment
                } else {
                    transaction.show(mMessage);
                    //显示之前隐藏的Fragment
                }
                mMessageImg.setImageResource(R.mipmap.message2);
                break;


            case 2:
                if (mMy == null)             //如果没有显示过
                {
                    mMy=new MyFragment();
                    transaction.add(R.id.id_content, mMy);
                    // 往Activity中添加一个Fragment,这里添加的是MenuFragment
                } else {
                    transaction.show(mMy);
                    //显示之前隐藏的Fragment
                }
                mMyImg.setImageResource(R.mipmap.my2);
                break;

            default:
                break;
        }
        transaction.commit();
        //提交一个事务
    }


    private void hideFragment(FragmentTransaction transaction) {
        if (mCommunity != null) {
            transaction.hide(mCommunity);//隐藏这个Fragment
        }
        if (mMessage != null) {
            transaction.hide(mMessage);
        }
        if (mMy != null) {
            transaction.hide(mMy);
        }
    }


    private void resetImgs() {
        mCommunityImg.setImageResource(R.mipmap.theme1);
        mMessageImg.setImageResource(R.mipmap.message1);
        mMyImg.setImageResource(R.mipmap.my1);

    }

    @Override
    public void remessage(String resulttype,JSONObject mes) {

    }

    //防止按返回键将avtivity finish掉
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            MainActivity.this.startActivity(intent);
            return false;
        } else return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
