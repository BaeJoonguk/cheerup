package com.example.user.cheerup.GetnSet;

/**
 * Created by user on 2017-01-16.
 */

public class MyQFragListItem {

    private int Qid;
    private String Qcontent;
    private String sender;

    public MyQFragListItem(int Qid, String qcontent, String sender) {
        this.Qid = Qid;
        this.Qcontent = qcontent;
        this.sender = sender;
    }

    public int getQidr() {return Qid;}

    public void setQid(String sender) {
        this.Qid = Qid;
    }

    public String getsender() {return sender;}

    public void setsender(String sender) {
        this.sender = sender;
    }

    public String getQcontent() {
        return Qcontent;
    }

    public void setQcontent(String content) {
        this.Qcontent = Qcontent;
    }

}
