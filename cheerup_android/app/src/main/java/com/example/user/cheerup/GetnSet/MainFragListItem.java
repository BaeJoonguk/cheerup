package com.example.user.cheerup.GetnSet;

import java.io.Writer;

/**
 * Created by user on 2017-01-16.
 */

/**
 * 클래스 이름 : public class MainFragListItem
 * 주요 기능 : 카드의 정보를 외부 매개변수를 통해 입력받아 책 객체를 형성하는 클래스
 * 멤버변수
 * private String Qcontent;
 private String Writer;
 private int pros_count=0;
 private int cons_count=0;
 */

public class MainFragListItem {

    private int CardNumber;

    //private int Qid;
    private String Qcontent;

    private String Writer;
    //private String sender;
    private int pros_count=0;
    private int cons_count=0;


    public int getPros_count() {return pros_count;}

    public void setPros_count(int pros_count) {this.pros_count = pros_count;}

    public int getCons_count() {return cons_count;}

    public void setCons_count(int cons_count) {this.cons_count = cons_count;}

    public MainFragListItem(String qcontent) {this.Qcontent = qcontent;}

    public MainFragListItem(int cardNumber, String qcontent) { this.CardNumber = cardNumber; this.Qcontent = qcontent;}

    public MainFragListItem(int cardNumber, String qcontent, String writer) { this.CardNumber = cardNumber; this.Qcontent = qcontent; this.Writer = writer;}

    public MainFragListItem(int cardNumber, String qcontent, String writer, int pros, int cons) { this.CardNumber = cardNumber; this.Qcontent = qcontent; this.Writer = writer; this.pros_count = pros; this.cons_count = cons;}

    public int getCardNumber() {return CardNumber;}

    //public void setQid(String sender) {this.Qid = Qid;}

    //public String getsender() {return sender;}

    //public void setsender(String sender) {this.sender = sender;}

    public String getQcontent() {return Qcontent;}

    public void setQcontent(String content) {
        this.Qcontent = Qcontent;
    }

}
