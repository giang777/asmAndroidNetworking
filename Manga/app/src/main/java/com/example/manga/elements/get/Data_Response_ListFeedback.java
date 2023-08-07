package com.example.manga.elements.get;

import com.example.manga.elements.child.Comments;
import com.example.manga.elements.child.Feedback;

import java.util.List;

public class Data_Response_ListFeedback {
    private List<Feedback> data;
    private String query;
    private String msg;
    private boolean success;

    public Data_Response_ListFeedback() {
    }

    public Data_Response_ListFeedback(List<Feedback> data, String query, String msg, boolean success) {
        this.data = data;
        this.query = query;
        this.msg = msg;
        this.success = success;
    }

    public List<Feedback> getData() {
        return data;
    }

    public void setData(List<Feedback> data) {
        this.data = data;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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
