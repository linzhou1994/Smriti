package com.linzhou.smriti.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.fragment
 *创建者:  linzhou
 *创建时间:17/04/21
 *描述:   fragment的基类
 */


public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(getlayoutId(),container,false);
        initView(view);
        setListener();
        initData();

        return view;
    }

    /**
     * 获取布局id
     * @return fragment的布局id
     */
    public abstract int getlayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

    /**
     * 加载数据
     */
    protected abstract void initData();

    /**
     * 设置监听事件
     */
    protected abstract void setListener();

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
