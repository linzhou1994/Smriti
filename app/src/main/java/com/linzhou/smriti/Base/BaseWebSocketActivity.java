package com.linzhou.smriti.Base;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Base
 *创建者:  linzhou
 *创建时间:17/07/10
 *描述:   
 */


import com.linzhou.smriti.websocekt.MyWebSocket;

public abstract class BaseWebSocketActivity extends BaseActivity implements MyWebSocket.TextMessageListener{

    @Override
    protected void setListener() {
        MyWebSocket.getWebSocket().addTextMessageListeners(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyWebSocket.getWebSocket().removeTextMessageListener(this);
    }
}
