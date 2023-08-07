package com.example.manga.elements.get;

import com.example.manga.elements.child.Comics;

public class Data_Response_Comics {
    private Comics data;
    private String msg;
    private boolean success;

    public Data_Response_Comics() {
    }

    public Data_Response_Comics(Comics data, String msg, boolean success) {
        this.data = data;
        this.msg = msg;
        this.success = success;
    }

    public Comics getData() {
        return data;
    }

    public void setData(Comics data) {
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
