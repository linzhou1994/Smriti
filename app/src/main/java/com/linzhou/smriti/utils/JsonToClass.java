package com.linzhou.smriti.utils;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Base
 *创建者:  linzhou
 *创建时间:17/07/30
 *描述:   
 */


import com.linzhou.smriti.Data.Firstreply;
import com.linzhou.smriti.Data.Profession;
import com.linzhou.smriti.Data.Secondreply;
import com.linzhou.smriti.Data.Theme;
import com.linzhou.smriti.Data.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonToClass {
    /**
     * 将json转化成User
     *
     * @param json
     * @return
     */
    public static User JsonToUser(JSONObject json) {
        User user = new User();
        if (json.optJSONObject("profession") != null)
            user.setProfession(JsonToProfession(json.optJSONObject("profession")));

        if (json.optString("tel") != null)
            user.setTel(json.optString("tel"));

        if (json.optString("username") != null)
            user.setUsername(json.optString("username"));


        user.setSex(json.optInt("sex"));

        if (json.optString("signature") != null)
            user.setSignature(json.optString("signature"));

        if (json.optString("head") != null){
            user.setHead(json.optString("head"));
            L.d("HEAD NO NULL  "+json.optString("head"));
        }

        if (json.optString("email") != null)
            user.setEmail(json.optString("email"));

        user.setId(json.optInt("userid"));

        return user;
    }

    /**
     * 将json转化成Profession
     *
     * @param json
     * @return
     */
    public static Profession JsonToProfession(JSONObject json) {
        Profession profession = new Profession();
        profession.setId(json.optInt("professionid"));
        if (json.optString("pname") != null)
            profession.setPName(json.optString("pname"));
        profession.setState(json.optInt("state"));
        return profession;
    }

    /**
     * 将json转化成Theme
     *
     * @param json
     * @return
     */
    public static Theme JsonToTheme(JSONObject json) throws JSONException {
        Theme mTheme = new Theme();
        mTheme.setId(json.optInt("t_id"));
        mTheme.setFirsttime(new Date(json.optLong("t_date")));
        mTheme.setReplienum(json.optInt("repnum"));
        mTheme.setContent(json.optString("t_content"));
        if (json.optJSONObject("user") != null){
            L.d("user NO NULL");
            mTheme.setUser(JsonToUser(json.optJSONObject("user")));
        }
        mTheme.setTitle(json.optString("t_title"));
        if (json.optJSONArray("firstReply") != null) {
            JSONArray jsonArray = json.optJSONArray("firstReply");
            List<Firstreply> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Firstreply firstreply = JsonToFirstreply(jsonArray.getJSONObject(i));
                list.add(firstreply);
            }
            mTheme.setFirstreplylies(list);
        }

        return mTheme;
    }

    public static Firstreply JsonToFirstreply(JSONObject json) {
        Firstreply firstreply = new Firstreply();
        firstreply.setId(json.optInt("frpid"));
        firstreply.setContent(json.optString("frpcontent"));
        firstreply.setTime(new Date(json.optLong("frptime")));
        firstreply.setType(json.optInt("frptype"));
        if (json.optJSONObject("frpuser") != null)
            firstreply.setUser(JsonToUser(json.optJSONObject("frpuser")));
        if (json.optJSONArray("secondreply") != null) {
            JSONArray jsonArray = json.optJSONArray("secondreply");
            List<Secondreply> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Secondreply secondreply = JsonToSecondreply(jsonArray.optJSONObject(i));
                list.add(secondreply);
            }
            firstreply.setSecondreplies(list);
        }
        return firstreply;
    }

    public static Secondreply JsonToSecondreply(JSONObject json) {
        Secondreply secondreply = new Secondreply();
        secondreply.setId(json.optInt("srpid"));
        secondreply.setTime(new Date(json.optLong("srptime")));
        secondreply.setContext(json.optString("srpcontext"));
        if (json.optJSONObject("srpuser") != null)
            secondreply.setuser(JsonToUser(json.optJSONObject("srpuser")));
        if (json.optJSONObject("srpuser_to_id") != null)
            secondreply.settouser(JsonToUser(json.optJSONObject("srpuser_to_id")));
        return secondreply;
    }
}
