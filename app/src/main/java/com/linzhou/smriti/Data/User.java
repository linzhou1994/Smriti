package com.linzhou.smriti.Data;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Data
 *创建者:  linzhou
 *创建时间:17/07/23
 *描述:   
 */


public class User {

    private int id;
    private Profession profession;
    private String tel;
    private String username;
    private Integer sex;
    private String signature;//个人签名
    private String head;//头像
    private String email;//邮箱

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "profession='" + profession.toString() + '\'' +
                ", tel='" + tel + '\'' +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", signature='" + signature + '\'' +
                ", head='" + head + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
