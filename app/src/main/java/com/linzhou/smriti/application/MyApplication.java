package com.linzhou.smriti.application;

import android.app.Application;

import com.linzhou.smriti.websocekt.MyWebSocket;


/*
 *项目名： Schentunion
 *包名：   com.linzhou.schentunion.application
 *创建者:  linzhou
 *创建时间:17/04/20
 *描述:   
 */


public class MyApplication extends Application {


    /**
     * 在app创建时调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        MyWebSocket.getWebSocket().init(getApplicationContext());


    }

    /**
     * 再app退出时调用
     */
    @Override
    public void onTerminate() {
        MyWebSocket.getWebSocket().close();
        super.onTerminate();
    }
}
