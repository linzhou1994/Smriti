package com.linzhou.smriti.Base;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Base
 *创建者:  linzhou
 *创建时间:17/07/30
 *描述:   
 */


import com.linzhou.smriti.Data.Profession;
import com.linzhou.smriti.Data.User;

import org.json.JSONObject;

public class JsonToClass {
    /**
     * 将json转化成User
     * @param json
     * @return
     */
    public static User JsonToUser(JSONObject json){
        User user = new User();
        user.setProfession(JsonToProfession(json.optJSONObject("profession")));
        user.setTel(json.optString("tel"));
        user.setUsername(json.optString("username"));
        user.setSex(json.optInt("sex"));
        user.setSignature(json.optString("signature"));
        user.setHead(json.optString("head"));
        user.setEmail(json.optString("email"));
        user.setId(json.optInt("userid"));
        return user;
    }

    /**
     * 将json转化成Profession
     * @param json
     * @return
     */
    public static Profession JsonToProfession(JSONObject json){
        Profession profession = new Profession();
        profession.setId(json.optInt("professionid"));
        profession.setPName(json.optString("pname"));
        profession.setState(json.optInt("state"));
        return profession;
    }
}
