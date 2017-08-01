package com.linzhou.smriti.Data;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Data
 *创建者:  linzhou
 *创建时间:17/07/30
 *描述:   
 */


public class Profession {

    private Integer id;
    private String PName;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Profession{" +
                "id=" + id +
                ", PName='" + PName + '\'' +
                ", state=" + state +
                '}';
    }
}
