package com.linzhou.smriti.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.linzhou.smriti.R;
import com.linzhou.smriti.utils.L;
import com.linzhou.smriti.utils.ShareUtils;

import butterknife.BindView;

/**
 * 闪烁页
 */
public class SplashActivity extends AppCompatActivity  {

    @BindView(R.id.image)
    public ImageView imageView;

    public static final int SPLASH = 10001;

    public static final int LOGIN = 10002;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SPLASH:
                    //Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    //startActivity(intent);
                    finish();
                    break;
                case LOGIN:
                    login();
                    break;
            }
        }
    };

    private void login() {


//        JSONObject json = new JSONObject();
//        try {
//            json.put(StaticClass.TYPE, StaticClass.LOGIN);
//            JSONObject content = new JSONObject();
//            content.put("tel", ShareUtils.getString(this, "name", ""));
//            content.put("password", ShareUtils.getString(this, "password", ""));
//            json.put(StaticClass.CONTENT, content);
//            MyWebSocket.webSocket.sendMessage(json.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("SplashActivity : onCreate");
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ShareUtils.getBoolean(SplashActivity.this,"login",true)){
            if (ShareUtils.getBoolean(SplashActivity.this, "keeppass", false)
                    && !TextUtils.isEmpty(ShareUtils.getString(this, "name", ""))
                    && !TextUtils.isEmpty(ShareUtils.getString(this, "password", ""))) {
                mHandler.sendEmptyMessageDelayed(LOGIN, 2000);

            } else {
                //延迟两秒
                mHandler.sendEmptyMessageDelayed(SPLASH, 2000);
            }
        }else {
            mHandler.sendEmptyMessageDelayed(SPLASH, 2000);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.d("SplashActivity : onDestroy");
    }
}
