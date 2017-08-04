package com.linzhou.smriti.utils;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.utils
 *创建者:  linzhou
 *创建时间:17/07/23
 *描述:   
 */


public class Url {

    public static final String IP ="120.24.70.156:8080/SmritiWeb";

    public static final String BASEURL = "http://"+IP;

    public static final String WEBSOCKETURL = "ws://"+IP+"/websocket";

    public static final String LOGIN = BASEURL+"/login";

    public static final String GETTHEMES = BASEURL+"/getThemes";

    public static final String HTTP_USER_HEAD_URL = "http://" + IP+"/";

    public static final String SEARCH_THEME = BASEURL+"/searchThemes";
    public static final String ADDTHEME = BASEURL+"/addTheme";

}
