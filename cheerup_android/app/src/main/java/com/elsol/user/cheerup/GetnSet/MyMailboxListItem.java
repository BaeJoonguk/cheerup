package com.elsol.user.cheerup.GetnSet;

/**
 * Created by user on 2017-01-03.
 */

public class MyMailboxListItem {

    private int commentid;
    private String content;
    private String time;
    private String sender;

    public String getsender() {return sender;}

    public void setsender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
