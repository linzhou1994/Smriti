package com.linzhou.smriti;

import com.linzhou.smriti.Data.Theme;
import com.linzhou.smriti.Data.User;
import com.linzhou.smriti.utils.L;
import com.linzhou.smriti.utils.OkHttp;
import com.linzhou.smriti.utils.Url;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Test {
    @org.junit.Test
    public void addition_isCorrect() throws Exception {
        List<Theme> date = new ArrayList<>();
        for (int i =0;i<=9;i++){
            Theme theme = new Theme();
            theme.setTitle("title"+i);
            theme.setFirsttime(new Date());
            theme.setContent("content content content content content"+i);
            theme.setReplienum(i);
            User user=new User();
            user.setUsername("username:"+i);
            theme.setUser(user);
            date.add(theme);
        }
        System.out.print(date.get(5).getTitle());
    }
}