package com.linzhou.smriti.Adapter;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Adapter
 *创建者:  linzhou
 *创建时间:17/08/21
 *描述:   
 */


import com.linzhou.smriti.Data.Firstreply;
import com.linzhou.smriti.Data.User;

public interface RepOnClickListener {

    /**
     *
     * @param firstreply 一级回复对象
     * @param user 回复对象
     */
    void onClickListener(Firstreply firstreply, User user);
}
