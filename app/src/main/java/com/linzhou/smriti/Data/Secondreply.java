package com.linzhou.smriti.Data;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Data
 *创建者:  linzhou
 *创建时间:17/08/08
 *描述:   
 */


import java.util.Date;

public class Secondreply {

    private Integer id;
    private User user;
    private User touser;
    private String context;
    private Date time;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getuser() {
        return user;
    }

    public void setuser(User userByUId) {
        this.user = userByUId;
    }

    public User gettouser() {
        return touser;
    }

    public void settouser(User userByUToId) {
        this.touser = userByUToId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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

    @Override
    public String toString() {
        return "Secondreply{" +
                "id=" + id +
                ", user=" + user.toString() +
                ", touser=" + touser +
                ", context='" + context + '\'' +
                ", time=" + time +
                ", state=" + state +
                '}';
    }
}
