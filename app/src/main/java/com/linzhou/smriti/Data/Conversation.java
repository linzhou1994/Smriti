package com.linzhou.smriti.Data;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * 佛曰:
 * 写字楼里写字间，写字间里程序员；
 * 程序人员写程序，又拿程序换酒钱。
 * 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。
 * 但愿老死电脑间，不愿鞠躬老板前；
 * 奔驰宝马贵者趣，公交自行程序员。
 * 别人笑我忒疯癫，我笑自己命太贱；
 * 不见满街漂亮妹，哪个归得程序员？
 * ---------------------------
 * 项目名： Smriti
 * 包名：   com.linzhou.smriti.Data
 * 创建者:  linzhou
 * 创建时间:17/09/04
 * 描述:
 */

public class Conversation {

    private int id;//会话id
    private int to_u_id;//聊天对象id
    private String username;//聊天对象名
    private String head;//聊天对象头像
    private long time;//最后一条消息时间
    private String msg;//最后一条消息内容
    private int unread;//消息未读数量


    public int getId() {
        return id;
    }

    public Conversation setId(int id) {
        this.id = id;
        return this;
    }

    public int getTo_u_id() {
        return to_u_id;
    }

    public Conversation setTo_u_id(int to_u_id) {
        this.to_u_id = to_u_id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Conversation setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getHead() {
        return head;
    }

    public Conversation setHead(String head) {
        this.head = head;
        return this;
    }

    public long getTime() {
        return time;
    }

    public Conversation setTime(long time) {
        this.time = time;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Conversation setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getUnread() {
        return unread;
    }

    public Conversation setUnread(int unread) {
        this.unread = unread;
        return this;
    }
}
