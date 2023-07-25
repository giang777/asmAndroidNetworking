package com.example.manga.elements.get;

import com.example.manga.elements.child.Chapters;
import com.example.manga.elements.child.Contents;

import java.util.List;

public class Data_Response_ListChapter {

    private Chapters data;
    private String msg;
    private boolean success;

    public Data_Response_ListChapter() {
    }

    public Data_Response_ListChapter(Chapters data, String msg, boolean success) {
        this.data = data;
        this.msg = msg;
        this.success = success;
    }

    public Chapters getData() {
        return data;
    }

    public void setData(Chapters data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
