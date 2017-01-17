package com.example.user.cheerup.GetnSet;

/**
 * Created by user on 2017-01-16.
 */

public class MainFragListItem {

    //private int Qid;
    private String Qcontent;
    //private String sender;

    public MainFragListItem(String qcontent) {

        this.Qcontent = qcontent;

    }

   // public int getQidr() {return Qid;}

    //public void setQid(String sender) {this.Qid = Qid;}

    //public String getsender() {return sender;}

    //public void setsender(String sender) {this.sender = sender;}

    public String getQcontent() {
        return Qcontent;
    }

    public void setQcontent(String content) {
        this.Qcontent = Qcontent;
    }

}
