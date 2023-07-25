package com.example.manga.elements.get;

import com.example.manga.elements.child.Chapters;
import com.example.manga.elements.child.Comics;

import java.util.List;

public class Data_Response_ListComics {
    private List<Comics> data;
    private String msg;
    private boolean success;

    public Data_Response_ListComics() {
    }

    public Data_Response_ListComics(List<Comics> data, String msg, boolean success) {
        this.data = data;
        this.msg = msg;
        this.success = success;
    }

    public List<Comics> getData() {
        return data;
    }

    public void setData(List<Comics> data) {
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
