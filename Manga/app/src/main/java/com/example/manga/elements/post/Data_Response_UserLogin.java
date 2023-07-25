package com.example.manga.elements.post;

import com.example.manga.elements.child.User;

import java.io.Serializable;

public class Data_Response_UserLogin implements Serializable {
    private User data;
    private String msg;
    private boolean success;

    public Data_Response_UserLogin() {
    }

    public Data_Response_UserLogin(User data, String msg, boolean success) {
        this.data = data;
        this.msg = msg;
        this.success = success;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
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
