package com.example.user.cheerup.model;

import android.app.LauncherActivity;

import com.example.user.cheerup.GetnSet.MyMailboxListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-01-05.
 */

public class TestData {

    private static final String[] sender = {"linda", "중욱", "엘솔"};
    private static final String[] content = {"할수있어 오늘도", "오늘 되게 예쁘네", "좋아해"};

    public static List<MyMailboxListItem> getListData() {
        List<MyMailboxListItem> data = new ArrayList<>();

        for (int n = 0; n < 3; n++) {
            for (int i = 0; i < sender.length && i < content.length; i++) {
                MyMailboxListItem item = new MyMailboxListItem();
                item.setsender(sender[i]);
                item.setContent(content[i]);
                data.add(item);
            }
        }
        return data;
    }
}
