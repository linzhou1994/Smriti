package com.linzhou.smriti;

import com.linzhou.smriti.utils.OkHttp;

import org.junit.Test;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String url = "http://192.168.0.151:8080/Inherit/login.action";
        //异步get-------------------------------------------------------------------------------
//        OkHttp.asynget(url, new OkHttp.OkHttpListener() {
//            @Override
//            public void success(String str) {
//                System.out.println(str);
//            }
//
//            @Override
//            public void error(String str) {
//                System.out.println(str);
//            }
//        });

        //异步post-------------------------------------------------------------------------------
//        OkHttp.asynpost(url, new OkHttp.OkHttpListener() {
//            @Override
//            public void success(String str) {
//                System.out.println(str);
//            }
//
//            @Override
//            public void error(String str) {
//                System.out.println(str);
//            }
//        });

        //同步get-------------------------------------------------------------------------------
//        OkHttp.get(url, new OkHttp.OkHttpListener() {
//            @Override
//            public void success(String str) {
//                System.out.println(str);
//            }
//
//            @Override
//            public void error(String str) {
//                System.out.println(str);
//            }
//        });
        //同步post-------------------------------------------------------------------------------
        RequestBody formBody = new FormBody.Builder()
        .add("password", "123456")
        .add("tel","13456115857")
        .build();
        OkHttp.post(url,formBody,new OkHttp.OkHttpListener() {
            @Override
            public void success(String str) {
                System.out.println(str);
            }

            @Override
            public void error(String str) {
                System.out.println(str);
            }
        });
        url = "http://localhost:8080/Inherit/getAllProfession";
        OkHttp.asynpost(url,new OkHttp.OkHttpListener() {
            @Override
            public void success(String str) {
                System.out.println(str);
            }

            @Override
            public void error(String str) {

            }
        });
        //---------------------------------------------------------------------------------------
        Thread.sleep(50000);
    }
}