package com.example.manga.elements.get;

import com.example.manga.elements.child.Blogs;
import com.example.manga.elements.child.Comics;

import java.util.List;

public class Data_Response_ListBlogs {
    private List<Blogs> data;
    private String msg;
    private boolean success;

    public Data_Response_ListBlogs() {
    }

    public Data_Response_ListBlogs(List<Blogs> data, String msg, boolean success) {
        this.data = data;
        this.msg = msg;
        this.success = success;
    }

    public List<Blogs> getData() {
        return data;
    }

    public void setData(List<Blogs> data) {
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
