package com.example.user.cheerup.model;

import android.app.LauncherActivity;

import com.example.user.cheerup.GetnSet.CommentListItem;
import com.example.user.cheerup.GetnSet.MyMailboxListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-01-05.
 */

public class TestData {

    private static final String[] content = {"할수있어 오늘도", "오늘 되게 예쁘네", "좋아해"};

    public static List<CommentListItem> getListData() {
        List<CommentListItem> data = new ArrayList<>();

        for (int n = 0; n < 3; n++) {
            for (int i = 0; i < content.length; i++) {
                CommentListItem item = new CommentListItem();
                item.setContent(content[i]);
                data.add(item);
            }
        }
        return data;
    }
}
