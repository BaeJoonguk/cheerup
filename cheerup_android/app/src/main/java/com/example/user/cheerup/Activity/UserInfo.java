package com.example.user.cheerup.Activity;

import java.io.Serializable;

/**
 * Created by user on 2017-01-26.
 */

public class UserInfo implements Serializable {
    public int userNumber;
    public String emailAddress;

    public UserInfo() { }

    public UserInfo(int userNumber, String emailAddress)
    {
        this.userNumber = userNumber;
        this.emailAddress = emailAddress;
    }
}
