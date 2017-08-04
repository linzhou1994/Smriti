package com.linzhou.smriti.utils;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Base
 *创建者:  linzhou
 *创建时间:17/07/30
 *描述:   
 */


import com.linzhou.smriti.Data.Profession;
import com.linzhou.smriti.Data.Theme;
import com.linzhou.smriti.Data.User;

import org.json.JSONObject;

import java.util.Date;

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

        if (json.optString("head") != null)
            user.setHead(json.optString("head"));

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
    public static Theme JsonToTheme(JSONObject json) {
        Theme mTheme = new Theme();
        mTheme.setId(json.optInt("t_id"));
        mTheme.setFirsttime(new Date(json.optLong("t_date")));
        mTheme.setReplienum(json.optInt("repnum"));
        mTheme.setContent(json.optString("t_content"));
        mTheme.setUser(JsonToUser(json.optJSONObject("user")));
        mTheme.setTitle(json.optString("t_title"));
        return mTheme;
    }

}
