package com.example.user.cheerup.GetnSet;

/**
 * Created by user on 2017-01-16.
 */

public class MainFragListItem {

    //private int Qid;
    private String Qcontent;
    //private String sender;
    private int pros_count=0;
    private int cons_count=0;


    public int getPros_count() {return pros_count;}

    public void setPros_count(int pros_count) {this.pros_count = pros_count;}

    public int getCons_count() {return cons_count;}

    public void setCons_count(int cons_count) {this.cons_count = cons_count;}

    public MainFragListItem(String qcontent) {this.Qcontent = qcontent;}

   // public int getQidr() {return Qid;}

    //public void setQid(String sender) {this.Qid = Qid;}

    //public String getsender() {return sender;}

    //public void setsender(String sender) {this.sender = sender;}

    public String getQcontent() {return Qcontent;}

    public void setQcontent(String content) {
        this.Qcontent = Qcontent;
    }

}