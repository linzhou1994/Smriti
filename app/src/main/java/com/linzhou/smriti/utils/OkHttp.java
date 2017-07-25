package com.linzhou.smriti.utils;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.utils
 *创建者:  linzhou
 *创建时间:17/07/10
 *描述:   
 */


import android.text.TextUtils;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp {

    private static OkHttpClient mOkHttpClient;

    private static String COOKIE ;

    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (OkHttp.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient();
                }
            }
        }
        return mOkHttpClient;
    }

    /**
     * OkHttp的get同步请求
     *
     * @param url            请求地址
     * @param okHttpListener 监听回调
     * @throws IOException
     */
    public static void get(String url, OkHttpListener okHttpListener) throws IOException {

        Request.Builder builder = new Request.Builder().url(url);
        setHeader(builder);
        Request request = builder.build();
        Response response = getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            setCOOKIE(response);
            if (okHttpListener != null) {
                okHttpListener.success(response.body().string());
            }
        } else {
            if (okHttpListener != null) {
                okHttpListener.error(response.toString());
            }
        }
    }

    /**
     * OkHttp的get异步请求
     *
     * @param url            请求地址
     * @param okHttpListener 监听回调
     * @throws IOException
     */
    public static void asynget(String url, OkHttpListener okHttpListener) throws IOException {

        Request.Builder builder = new Request.Builder().url(url);

        setHeader(builder);
        Request request = builder.build();

        getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("error");
                if (okHttpListener != null)
                    okHttpListener.error(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                setCOOKIE(response);
                if (okHttpListener != null) {
                    okHttpListener.success(response.body().string());
                }
            }

        });

    }

    /**
     * post同步请求
     *
     * @param url            请求地址
     * @param requestBody    参数内容
     * @param okHttpListener 监听回调
     * @throws IOException
     */
    public static void post(String url, RequestBody requestBody, OkHttpListener okHttpListener) throws IOException {
//        RequestBody formBody = new FormBody.Builder()
//                .add("search", "Jurassic Park")
//                .build();


        Request.Builder builder = new Request.Builder().url(url);
        if (requestBody != null)
            builder.post(requestBody);

        setHeader(builder);
        Request request = builder.build();

        Response response = getOkHttpClient().newCall(request).execute();

        if (response.isSuccessful()) {
            setCOOKIE(response);
            if (okHttpListener != null) {
                okHttpListener.success(response.body().string());
            }
        } else {
            if (okHttpListener != null) {
                okHttpListener.error(response.toString());
            }
        }
    }

    public static void post(String url, OkHttpListener okHttpListener) throws IOException {
        post(url, null, okHttpListener);
    }

    /**
     * post异步请求
     *
     * @param url            请求地址
     * @param requestBody    参数内容
     * @param okHttpListener 监听回调
     * @throws IOException
     */
    public static void asynpost(String url, RequestBody requestBody, OkHttpListener okHttpListener) throws IOException {

        Request.Builder builder = new Request.Builder().url(url);
        if (requestBody != null)
            builder.post(requestBody);
        setHeader(builder);
        Request request = builder.build();

        getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okHttpListener != null) {
                    okHttpListener.error(e.toString());
                }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                setCOOKIE(response);
                if (okHttpListener != null) {
                    okHttpListener.success(response.body().string());
                }
            }
        });
    }

    public static void asynpost(String url, OkHttpListener okHttpListener) throws IOException {
        asynpost(url, null, okHttpListener);
    }

    private static void setHeader(Request.Builder builder){
        if (COOKIE != null && !COOKIE.equals(""))
            builder.addHeader("Cookie", COOKIE)
                    .addHeader("Connection", "keep-alive");
    }


    private static void setCOOKIE(Response response) {
        String cookie = response.header("Set-Cookie");
        if (cookie==null) return;
            cookie = cookie.substring(0, cookie.indexOf(";"));
        System.out.println(COOKIE + " " + cookie);
        if (COOKIE == null || !cookie.equals(COOKIE)) {
            COOKIE = cookie;
        }

    }


    public interface OkHttpListener {

        void success(String str);

        void error(String str);
    }

}
