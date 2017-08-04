package com.linzhou.smriti.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.linzhou.smriti.Base.AppConfig;
import com.linzhou.smriti.Base.BaseActivity;
import com.linzhou.smriti.utils.JsonToClass;
import com.linzhou.smriti.Base.StaticClass;
import com.linzhou.smriti.R;
import com.linzhou.smriti.View.CustomDialog;
import com.linzhou.smriti.utils.L;
import com.linzhou.smriti.utils.OkHttp;
import com.linzhou.smriti.utils.Url;
import com.linzhou.smriti.websocekt.MyWebSocket;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.RequestBody;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Activity
 *创建者:  linzhou
 *创建时间:17/07/10
 *描述:   
 */


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 登录成功码
     */
    private static final String LOGIN_SUCCESS="Login_success";
    /**
     * 登录失败码
     */
    private static final String LOGIN_ERROR="Login_fail";

    private EditText mInputTel;
    private EditText mInputPassword;
    private AppCompatButton mBtnLogin;
    private LinearLayout ll_root;
    private TextView mTvSignup;

    private CustomDialog mLoginDialog;

    @Override
    protected int getlayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initview() {
        mInputTel = (EditText) findViewById(R.id.input_tel);
        mInputPassword = (EditText) findViewById(R.id.input_password);
        mBtnLogin = (AppCompatButton) findViewById(R.id.btn_login);
        ll_root = (LinearLayout) findViewById(R.id.ll_root);
        mTvSignup = (TextView) findViewById(R.id.tv_signup);
        mLoginDialog = new CustomDialog(this, 0, 0, R.layout.dialog_loding,
                R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        mLoginDialog.setCancelable(false);//弹窗外不可点击

        applyBlur();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_signup:

                break;
        }
    }

    /**
     * 登录逻辑
     */
    private void login() throws IOException {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        mBtnLogin.setEnabled(false);
        mLoginDialog.show();

        String tel = mInputTel.getText().toString().trim();
        String password = mInputPassword.getText().toString().trim();

        RequestBody loginbody = new FormBody.Builder()
                .add("password", password)
                .add("tel",tel)
                .build();

        OkHttp.asynpost(Url.LOGIN, loginbody, new OkHttp.OkHttpListener() {
            @Override
            public void success(String str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLoginDialog.dismiss();
                        try {
                            JSONObject loginjson = new JSONObject(str);
                            String result = loginjson.optString(StaticClass.RESULT);
                            switch (result) {
                                case LOGIN_ERROR://登录失败
                                    mBtnLogin.setEnabled(true);
                                    Toast.makeText(LoginActivity.this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
                                    break;
                                case LOGIN_SUCCESS://登录成功
                                    JSONObject resultuser = loginjson.optJSONObject(StaticClass.CONTENT);
                                    loginsuccess(resultuser);
                                    break;
                                default:
                                    mBtnLogin.setEnabled(true);
                                    Toast.makeText(LoginActivity.this, "未知错误导致登录异常", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }

            @Override
            public void error(String str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLoginDialog.dismiss();
                        mBtnLogin.setEnabled(true);
                        Toast.makeText(LoginActivity.this, "网络异常,登录失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void loginsuccess(JSONObject resultuser) {
        AppConfig.mUser = JsonToClass.JsonToUser(resultuser);
        L.d(AppConfig.mUser.getId()+"  AppConfig.mUser.getId");
        mLoginDialog.dismiss();
        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
        L.d(AppConfig.mUser.toString());
        MyWebSocket.getWebSocket().openWebsocket();

        startActivity(MainActivity.class);
        finish();
    }

    /**
     * 邮箱，密码是否格式正确
     * @return
     */
    public boolean validate() {
        boolean valid = true;

        String tel = mInputTel.getText().toString();
        String password = mInputPassword.getText().toString();

        if (tel.isEmpty() || !Patterns.PHONE.matcher(tel).matches()) {
            mInputTel.setError("请输入有效的手机号码");
            valid = false;
        } else {
            mInputTel.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mInputPassword.setError("密码长度在4-10位之间");
            valid = false;
        } else {
            mInputPassword.setError(null);
        }

        return valid;
    }

    /**
     * 格式错误导致登录失败逻辑
     */
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "登录失败", Toast.LENGTH_SHORT).show();

        mBtnLogin.setEnabled(true);
    }


    private int radius = 25;
    private void applyBlur() {
        Drawable db = getResources().getDrawable(R.mipmap.login_bg);
        BitmapDrawable drawable = (BitmapDrawable) db;
        Bitmap bgBitmap = drawable.getBitmap();
        //处理得到模糊效果的图
        RenderScript renderScript = RenderScript.create(this);
        final Allocation input = Allocation.createFromBitmap(renderScript, bgBitmap);
        final Allocation output = Allocation.createTyped(renderScript, input.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);
        scriptIntrinsicBlur.setRadius(radius);
        scriptIntrinsicBlur.forEach(output);
        output.copyTo(bgBitmap);
        renderScript.destroy();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bgBitmap);
        ll_root.setBackground(bitmapDrawable);
    }

    @Override
    protected void setListener() {
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

}
