package com.linzhou.smriti.Data;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Data
 *创建者:  linzhou
 *创建时间:17/08/08
 *描述:   
 */


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Firstreply {

    // Fields

    private Integer id;
    private Theme theme;
    private User user;
    private String content;
    private Integer type;
    private Date time;
    private Integer state;
    private List<Secondreply> secondreplist = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Secondreply> getSecondreplies() {
        return secondreplist;
    }

    public void setSecondreplies(List<Secondreply> secondreplies) {
        this.secondreplist = secondreplies;
    }
}
