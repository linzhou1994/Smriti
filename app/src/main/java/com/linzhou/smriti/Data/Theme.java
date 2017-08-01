package com.linzhou.smriti.Data;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Data
 *创建者:  linzhou
 *创建时间:17/07/31
 *描述:   
 */


import java.util.Date;

public class Theme {

    private Integer id;
    private User user;
    private Integer PId;
    private String title;
    private String content;
    private Date firsttime;
    private Date lasttime;
    private Integer replienum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPId() {
        return PId;
    }

    public void setPId(Integer PId) {
        this.PId = PId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Integer getReplienum() {
        return replienum;
    }

    public void setReplienum(Integer replienum) {
        this.replienum = replienum;
    }


    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", user=" + user.toString() +
                ", PId=" + PId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firsttime=" + firsttime +
                ", lasttime=" + lasttime +
                ", replienum=" + replienum +
                '}';
    }
}
